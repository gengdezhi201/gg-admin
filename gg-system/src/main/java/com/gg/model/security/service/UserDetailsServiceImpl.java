package com.gg.model.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @Override
    public SysUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name",username));
        List<GrantedAuthority> authorities;
        Set<String> set = new HashSet<>();
        set.add("ROLE_ADMIN");
        authorities=AuthorityUtils.createAuthorityList(set.toArray(new String[0]));
        return new SysUserDetails(sysUser.getUserId(), sysUser.getUserName(), sysUser.getPassword(), true, true, true, true,
                authorities);
    }
}