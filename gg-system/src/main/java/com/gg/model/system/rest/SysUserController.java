package com.gg.model.system.rest;

import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.JwtProperties;
import com.gg.model.security.util.SysUserUtil;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import com.gg.model.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping(value = "/system/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 获取用户列表 分页
     * @param sysUserQueryCriteria
     * @return
     */
    @GetMapping
    @PreAuthorize("@gg.check('system:user:list')")
    public ResultEntity page(SysUserQueryCriteria sysUserQueryCriteria){
        return ResultEntity.success(sysUserService.getSysUserListPage(sysUserQueryCriteria));
    }

    /**
     * 添加用户
     * @param sysUser
     * @return
     */
    @PostMapping
    @PreAuthorize("@gg.check('system:user:add')")
    public ResultEntity add(@RequestBody SysUser sysUser){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysUser.setCreateUserName(loginUser.getUserName());
        if(sysUserService.save(sysUser)){
            return ResultEntity.success("添加成功");
        }else{
            return ResultEntity.fail("添加失败");
        }
    }

    /**
     * 修改用户
     * @param sysUser
     * @return
     */
    @PutMapping
    @PreAuthorize("@gg.check('system:user:edit')")
    public ResultEntity edit(@RequestBody SysUser sysUser){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysUser.setCreateUserName(loginUser.getUserName());
        if(sysUserService.updateById(sysUser)){
            return ResultEntity.success("修改成功");
        }else{
            return ResultEntity.fail("修改失败");
        }
    }

    /**
     * 删除用户
     * @param userIds
     * @return
     */
    @DeleteMapping
    @PreAuthorize("@gg.check('system:user:del')")
    public ResultEntity del(String userIds){
        if(sysUserService.removeByIds(Arrays.asList(userIds.split(",")))){
            return ResultEntity.success("删除成功");
        }else{
            return ResultEntity.fail("删除失败");
        }
    }

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    @PreAuthorize("@gg.check('system:user:list')")
    public ResultEntity get(@PathVariable("userId")Long userId){
        return ResultEntity.success(sysUserService.getById(userId));
    }

}
