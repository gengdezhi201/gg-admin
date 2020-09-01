package com.gg.model.system.rest;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.security.util.JwtUtil;
import com.gg.model.system.domain.SysLogin;
import com.gg.model.system.service.ISysMenuService;
import com.gg.model.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;

@RestController
@RequestMapping("/auth")
public class SysLoginController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/login")
    public ResultEntity login(@Validated @RequestBody SysLogin sysLogin){
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysLogin.getUsername(),sysLogin.getPassword());
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException){
                return ResultEntity.fail("登录出错",e.getMessage());
            }else{
                e.printStackTrace();
            }
        }
        SysUserDetails sysUserDetails = (SysUserDetails)authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.createToken(authentication);
        jwtUtil.setLoginUser(token,sysUserDetails);
        return ResultEntity.success("登录成功",token);
    }

    @DeleteMapping("/logout")
    public ResultEntity logout(HttpServletRequest request){
        jwtUtil.delLoginUser(request);
        return ResultEntity.success("登出成功");
    }

    @GetMapping("/getUserInfo")
    public ResultEntity getUserInfo(HttpServletRequest request){
        return ResultEntity.success(jwtUtil.getLoginUser(jwtUtil.getToken(request)));
    }

    @GetMapping("/getImage")
    @PreAuthorize("@gg.check('user:list')")
    public ResultEntity cs(HttpServletRequest request){
        BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
        IoUtil.readBytes(in);
        return null;
    }

    @GetMapping("/cc")
    @PreAuthorize("@gg.check('user:list')")
    public ResultEntity cc(HttpServletRequest request){
        return ResultEntity.success(sysUserService.list());
    }

    @GetMapping("getRouters")
    public ResultEntity getRouters(){
        return ResultEntity.success(sysMenuService.getSysMenuTreeByUserId(1));
    }
}
