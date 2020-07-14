package com.gg.model.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysUserMapper;
import com.gg.model.system.rest.SysUserController;
import com.gg.model.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public SysUserDetails login(String username, String password) {
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            authentication = this.authenticationManager.authenticate(authenticationToken);
        } catch (Exception var5) {
            var5.printStackTrace();
        }
        SysUserDetails sysUser = (SysUserDetails)authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("-*-*-*-*-*-" + SysUserController.getLoginUser());
        return sysUser;
    }
}
