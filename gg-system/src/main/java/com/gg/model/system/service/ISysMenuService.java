package com.gg.model.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.model.system.domain.SysMenu;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.domain.vo.RouterVo;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
public interface ISysMenuService extends IService<SysMenu> {

    List<RouterVo> getSysMenuTreeByUserId(int userId);

    Page getSysMenuListPage(SysUserQueryCriteria sysUserQueryCriteria);
}
