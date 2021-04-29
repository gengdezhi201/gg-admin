package com.gg.model.system.rest;

import com.gg.domain.ResultEntity;
import com.gg.model.security.util.SysUserUtil;
import com.gg.model.system.domain.SysDict;
import com.gg.model.system.domain.SysDictData;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysDictDataQueryCriteria;
import com.gg.model.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 数据字典详情 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/system/sysDictData")
public class SysDictDataController{

    @Autowired
    ISysDictDataService sysDictDataService;

    /**
     * 获取数据字典详情列表 分页
     * @return
     */
    @GetMapping
    @PreAuthorize("@gg.check('system:dict:detail')")
    public ResultEntity page(SysDictDataQueryCriteria sysDictDataQueryCriteria){
        return ResultEntity.success(sysDictDataService.getSysDictDataListPage(sysDictDataQueryCriteria));
    }

    /**
     * 添加字典详情列表
     * @return
     */
    @PostMapping
    @PreAuthorize("@gg.check('system:dict:add')")
    public ResultEntity add(@RequestBody SysDictData sysDictData){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysDictData.setCreateUserName(loginUser.getUserName());
        if(sysDictDataService.save(sysDictData)){
            return ResultEntity.success("添加成功");
        }else{
            return ResultEntity.fail("添加失败");
        }
    }

    /**
     * 修改字典详情
     * @param sysDictData
     * @return
     */
    @PutMapping
    @PreAuthorize("@gg.check('system:dict:edit')")
    public ResultEntity edit(@RequestBody SysDictData sysDictData){
        SysUser loginUser = SysUserUtil.getLoginUser().getUser();
        sysDictData.setCreateUserName(loginUser.getUserName());
        if(sysDictDataService.updateById(sysDictData)){
            return ResultEntity.success("修改成功");
        }else{
            return ResultEntity.fail("修改失败");
        }
    }

    /**
     * 根据字典详情ID获取字典详情
     * @return
     */
    @GetMapping("/{detailId}")
    @PreAuthorize("@gg.check('system:dict:list')")
    public ResultEntity get(@PathVariable Integer detailId){
        return ResultEntity.success(sysDictDataService.getById(detailId));
    }

    /**
     * 删除字典
     * @param detailIds
     * @return
     */
    @DeleteMapping
    @PreAuthorize("@gg.check('system:dict:del')")
    public ResultEntity del(String detailIds){
        if(sysDictDataService.removeByIds(Arrays.asList(detailIds.split(",")))){
            return ResultEntity.success("删除成功");
        }else{
            return ResultEntity.fail("删除失败");
        }
    }
}
