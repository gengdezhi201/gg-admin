package com.gg.model.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.security.util.JwtUtil;
import com.gg.model.system.domain.SysLogin;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysUserMapper;
import com.gg.model.system.service.ISysUserService;
import com.gg.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 *  服务实现类
 * @author gengdz
 * 2020年7月17日13:42:56
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    public ResultEntity login(SysLogin sysLogin) {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(sysLogin.getUsername(),sysLogin.getPassword());
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException){
                return ResultEntity.fail(e.getMessage());
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
}
