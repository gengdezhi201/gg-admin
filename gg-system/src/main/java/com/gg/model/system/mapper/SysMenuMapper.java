package com.gg.model.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gg.model.system.domain.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    Set<String> getPermissionByUserId(Integer userId);

}
