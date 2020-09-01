package com.gg.model.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.model.system.domain.SysRole;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
public interface ISysRoleService extends IService<SysRole> {

    Page getSysRoleListPage(SysUserQueryCriteria sysUserQueryCriteria);

}
