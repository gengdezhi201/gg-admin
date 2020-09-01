package com.gg.model.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.system.domain.SysMenu;
import com.gg.model.system.domain.SysRole;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.mapper.SysRoleMapper;
import com.gg.model.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;
    @Override
    public Page getSysRoleListPage(SysUserQueryCriteria sysUserQueryCriteria) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper();
//        wrapper
//                .like(sysUserQueryCriteria.getUsername()!=null,"username",sysUserQueryCriteria.getUsername())
//                .like(sysUserQueryCriteria.getUsername()!=null,"nickname",sysUserQueryCriteria.getUsername())
//                .eq(sysUserQueryCriteria.getDeptId()!=null,"dept_id",sysUserQueryCriteria.getDeptId())
//                .eq(sysUserQueryCriteria.getStatus()!=null,"status",sysUserQueryCriteria.getStatus());
        return sysRoleMapper.selectPage(new Page(sysUserQueryCriteria.getPageNum(),sysUserQueryCriteria.getPageSize()),wrapper);
    }
}
