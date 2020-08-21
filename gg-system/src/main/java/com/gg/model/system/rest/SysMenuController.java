package com.gg.model.system.rest;


import com.gg.domain.ResultEntity;
import com.gg.model.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/system/sys-menu")
public class SysMenuController{

    @Autowired
    ISysMenuService sysMenuService;

    @GetMapping
    @PreAuthorize("@gg.check('user:dd')")
    public ResultEntity getSysMenuAll(){
        return ResultEntity.success(sysMenuService.list());
    }

}
