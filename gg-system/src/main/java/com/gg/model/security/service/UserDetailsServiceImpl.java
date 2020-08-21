package com.gg.model.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.security.domain.SysUserDetailsbf2;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysMenuMapper;
import com.gg.model.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public SysUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name",username));
        Set<String> permission = sysMenuMapper.getPermissionByUserId(sysUser.getUserId());
        return new SysUserDetails(sysUser,permission);
    }
}