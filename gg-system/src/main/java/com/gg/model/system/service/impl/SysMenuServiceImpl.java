package com.gg.model.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.system.domain.SysMenu;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.domain.vo.MetaVo;
import com.gg.model.system.domain.vo.RouterVo;
import com.gg.model.system.mapper.SysMenuMapper;
import com.gg.model.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<RouterVo> getSysMenuTreeByUserId(int userId) {
        List<SysMenu> list = sysMenuMapper.selectList(new QueryWrapper<SysMenu>().eq("type","0").or().eq("type","1"));
        return buildMenus(list, null);
    }

    public List<RouterVo> buildMenus(List<SysMenu> menus, Long pid) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus) {
            if (menu.getPid() == pid) {
                RouterVo router = new RouterVo();
                router.setHidden("1".equals(menu.getHidden()));
                if (menu.getName() == null) {
                    router.setName(menu.getTitle());
                } else {
                    router.setName(menu.getName());
                }
                if (menu.getPath() != null && !"".equals(menu.getPath())) {
                    if (menu.getPath().indexOf("/") == 0) {
                        router.setPath(menu.getPath());
                    } else {
                        router.setPath("/" + menu.getPath());
                    }
                }
                if (menu.getComponent() == null || "".equals(menu.getComponent())) {
                    router.setComponent("Layout");
                    router.setRedirect("noRedirect");
                } else {
                    router.setComponent(menu.getComponent());
                }
                router.setMeta(new MetaVo(menu.getTitle(), menu.getIcon()));
                List<RouterVo> cMenus = buildMenus(menus, menu.getMenuId());
                if (cMenus != null && cMenus.size() > 0) router.setAlwaysShow(true);
                router.setChildren(cMenus);
                routers.add(router);
            }
        }
        return routers;
    }

    @Override
    public Page getSysMenuListPage(SysUserQueryCriteria sysUserQueryCriteria) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper();
//        wrapper
//                .like(sysUserQueryCriteria.getUsername()!=null,"username",sysUserQueryCriteria.getUsername())
//                .like(sysUserQueryCriteria.getUsername()!=null,"nickname",sysUserQueryCriteria.getUsername())
//                .eq(sysUserQueryCriteria.getDeptId()!=null,"dept_id",sysUserQueryCriteria.getDeptId())
//                .eq(sysUserQueryCriteria.getStatus()!=null,"status",sysUserQueryCriteria.getStatus());
        return sysMenuMapper.selectPage(new Page(sysUserQueryCriteria.getPageNum(),sysUserQueryCriteria.getPageSize()),wrapper);
    }
}
