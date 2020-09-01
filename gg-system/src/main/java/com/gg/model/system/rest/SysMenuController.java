package com.gg.model.system.rest;


import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.JwtProperties;
import com.gg.model.security.util.SysUserUtil;
import com.gg.model.system.domain.SysMenu;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.service.ISysMenuService;
import com.gg.model.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.Arrays;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/system/sysMenu")
public class SysMenuController{

    @Autowired
    private ISysMenuService sysMenuService;

    /**
     * 获取菜单列表 分页
     * @param sysUserQueryCriteria
     * @return
     */
    @GetMapping
    @PreAuthorize("@gg.check('system:menu:list')")
    public ResultEntity page(SysUserQueryCriteria sysUserQueryCriteria){
        return ResultEntity.success(sysMenuService.getSysMenuListPage(sysUserQueryCriteria));
    }

    /**
     * 添加菜单
     * @param sysMenu
     * @return
     */
    @PostMapping
    @PreAuthorize("@gg.check('system:menu:add')")
    public ResultEntity add(@RequestBody SysMenu sysMenu){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysMenu.setCreateUserName(loginUser.getUserName());
        if(sysMenuService.save(sysMenu)){
            return ResultEntity.success("添加成功");
        }else{
            return ResultEntity.fail("添加失败");
        }
    }

    /**
     * 修改菜单
     * @param sysMenu
     * @return
     */
    @PutMapping
    @PreAuthorize("@gg.check('system:menu:edit')")
    public ResultEntity edit(@RequestBody SysMenu sysMenu){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysMenu.setCreateUserName(loginUser.getUserName());
        if(sysMenuService.updateById(sysMenu)){
            return ResultEntity.success("修改成功");
        }else{
            return ResultEntity.fail("修改失败");
        }
    }

    /**
     * 删除菜单
     * @param menuIds
     * @return
     */
    @DeleteMapping
    @PreAuthorize("@gg.check('system:menu:del')")
    public ResultEntity del(String menuIds){
        if(sysMenuService.removeByIds(Arrays.asList(menuIds.split(",")))){
            return ResultEntity.success("删除成功");
        }else{
            return ResultEntity.fail("删除失败");
        }
    }

    /**
     * 根据菜单ID查询菜单
     * @param menuId
     * @return
     */
    @GetMapping("/{menuId}")
    @PreAuthorize("@gg.check('system:menu:list')")
    public ResultEntity get(@PathVariable("menuId")Long menuId){
        return ResultEntity.success(sysMenuService.getById(menuId));
    }

}
