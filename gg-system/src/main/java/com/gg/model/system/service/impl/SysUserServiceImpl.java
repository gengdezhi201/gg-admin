package com.gg.model.system.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.security.util.JwtUtil;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysUserMapper;
import com.gg.model.system.service.ISysUserService;
import com.gg.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

    public SysUserDetails login(String username, String password) {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        SysUserDetails sysUserDetails = (SysUserDetails)authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.createToken(authentication);
        jwtUtil.setLoginUser(token,sysUserDetails);
        return sysUserDetails;
    }
}
