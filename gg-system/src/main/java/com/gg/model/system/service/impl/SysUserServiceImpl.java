package com.gg.model.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.security.util.JwtUtil;
import com.gg.model.system.domain.SysLogin;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.mapper.SysUserMapper;
import com.gg.model.system.service.ISysUserService;
import com.gg.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


/**
 *  服务实现类
 * @author gengdz
 * 2020年7月17日13:42:56
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Page getSysUserListPage(SysUserQueryCriteria sysUserQueryCriteria) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper
                .like(sysUserQueryCriteria.getUsername()!=null,"username",sysUserQueryCriteria.getUsername())
                .like(sysUserQueryCriteria.getUsername()!=null,"nickname",sysUserQueryCriteria.getUsername())
                .eq(sysUserQueryCriteria.getDeptId()!=null,"dept_id",sysUserQueryCriteria.getDeptId())
                .eq(sysUserQueryCriteria.getStatus()!=null,"status",sysUserQueryCriteria.getStatus());
        return sysUserMapper.selectPage(new Page(sysUserQueryCriteria.getPageNum(),sysUserQueryCriteria.getPageSize()),wrapper);
    }
}
