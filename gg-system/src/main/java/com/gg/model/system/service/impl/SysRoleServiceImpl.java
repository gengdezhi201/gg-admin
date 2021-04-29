package com.gg.model.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.system.domain.SysRole;
import com.gg.model.system.domain.dto.query.SysRoleQueryCriteria;
import com.gg.model.system.mapper.SysRoleMapper;
import com.gg.model.system.service.ISysRoleService;
import com.gg.util.StringUtils;
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
    public Page getSysRoleListPage(SysRoleQueryCriteria sysRoleQueryCriteria) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper();
        wrapper
                .like(StringUtils.isNotEmpty(sysRoleQueryCriteria.getRoleName()),"role_name",sysRoleQueryCriteria.getRoleName())
                .eq(StringUtils.isNotEmpty(sysRoleQueryCriteria.getStatus()),"status",sysRoleQueryCriteria.getStatus());
        return sysRoleMapper.selectPage(new Page(sysRoleQueryCriteria.getPageNum(),sysRoleQueryCriteria.getPageSize()),wrapper);
    }
}
