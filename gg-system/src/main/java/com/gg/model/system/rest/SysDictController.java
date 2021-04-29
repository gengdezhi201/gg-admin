package com.gg.model.system.rest;

import com.gg.domain.ResultEntity;
import com.gg.model.security.util.SysUserUtil;
import com.gg.model.system.domain.SysDict;
import com.gg.model.system.domain.SysMenu;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysDictQueryCriteria;
import com.gg.model.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/system/sysDict")
public class SysDictController{

    @Autowired
    ISysDictService sysDictService;

    /**
     * 获取数据字典列表 分页
     * @return
     */
    @GetMapping
    @PreAuthorize("@gg.check('system:dict:list')")
    public ResultEntity page(SysDictQueryCriteria sysDictQueryCriteria){
        return ResultEntity.success(sysDictService.getSysDictListPage(sysDictQueryCriteria));
    }

    /**
     * 根据字典ID获取数据字典
     * @return
     */
    @GetMapping("/{dictId}")
    @PreAuthorize("@gg.check('system:dict:list')")
    public ResultEntity get(@PathVariable Integer dictId){
        return ResultEntity.success(sysDictService.getById(dictId));
    }

    /**
     * 添加数据字典列表
     * @return
     */
    @PostMapping
    @PreAuthorize("@gg.check('system:dict:add')")
    public ResultEntity add(@RequestBody SysDict sysDict){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysDict.setCreateUserName(loginUser.getUserName());
        if(sysDictService.save(sysDict)){
            return ResultEntity.success("添加成功");
        }else{
            return ResultEntity.fail("添加失败");
        }
    }

    /**
     * 修改字典
     * @param sysDict
     * @return
     */
    @PutMapping
    @PreAuthorize("@gg.check('system:dict:edit')")
    public ResultEntity edit(@RequestBody SysDict sysDict){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysDict.setCreateUserName(loginUser.getUserName());
        if(sysDictService.updateById(sysDict)){
            return ResultEntity.success("修改成功");
        }else{
            return ResultEntity.fail("修改失败");
        }
    }

    /**
     * 删除字典
     * @param dictIds
     * @return
     */
    @DeleteMapping
    @PreAuthorize("@gg.check('system:dict:del')")
    public ResultEntity del(String dictIds){
        if(sysDictService.removeByIds(Arrays.asList(dictIds.split(",")))){
            return ResultEntity.success("删除成功");
        }else{
            return ResultEntity.fail("删除失败");
        }
    }
}
