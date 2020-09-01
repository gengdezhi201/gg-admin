package com.gg.model.system.rest;


import com.gg.domain.ResultEntity;
import com.gg.model.security.util.SysUserUtil;
import com.gg.model.system.domain.SysRole;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@RestController
@RequestMapping("/system/sysRole")
public class SysRoleController{

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 获取角色列表 分页
     * @param sysUserQueryCriteria
     * @return
     */
    @GetMapping
    @PreAuthorize("@gg.check('system:role:list')")
    public ResultEntity page(SysUserQueryCriteria sysUserQueryCriteria){
        return ResultEntity.success(sysRoleService.list());
    }

    /**
     * 添加角色
     * @param sysRole
     * @return
     */
    @PostMapping
    @PreAuthorize("@gg.check('system:role:add')")
    public ResultEntity add(@RequestBody SysRole sysRole){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysRole.setCreateUserName(loginUser.getUserName());
        if(sysRoleService.save(sysRole)){
            return ResultEntity.success("添加成功");
        }else{
            return ResultEntity.fail("添加失败");
        }
    }

    /**
     * 修改角色
     * @param sysRole
     * @return
     */
    @PutMapping
    @PreAuthorize("@gg.check('system:role:edit')")
    public ResultEntity edit(@RequestBody SysRole sysRole){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysRole.setCreateUserName(loginUser.getUserName());
        if(sysRoleService.updateById(sysRole)){
            return ResultEntity.success("修改成功");
        }else{
            return ResultEntity.fail("修改失败");
        }
    }

    /**
     * 删除角色
     * @param roleIds
     * @return
     */
    @DeleteMapping
    @PreAuthorize("@gg.check('system:role:del')")
    public ResultEntity del(String roleIds){
        if(sysRoleService.removeByIds(Arrays.asList(roleIds.split(",")))){
            return ResultEntity.success("删除成功");
        }else{
            return ResultEntity.fail("删除失败");
        }
    }

    /**
     * 根据角色ID查询角色
     * @param roleId
     * @return
     */
    @GetMapping("/{roleId}")
    @PreAuthorize("@gg.check('system:role:list')")
    public ResultEntity get(@PathVariable("roleId")Long roleId){
        return ResultEntity.success(sysRoleService.getById(roleId));
    }
}
