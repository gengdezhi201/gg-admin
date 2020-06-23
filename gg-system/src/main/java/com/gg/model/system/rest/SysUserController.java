package com.gg.model.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysUserMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysUserController {

    @Autowired
    private SysUserMapper sysUserMapper;

    @GetMapping("/demo")
    public SysUser demo(){
        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name","admin"));
        if (sysUser != null) {
            System.out.println(sysUser.toString());
            System.out.println(sysUser.getCreateTime());
        }
        return sysUser;
    }

    @ApiOperation(value = "获取新的订单信息")
    @GetMapping(value = "order")
    public String getOrder(@ApiParam(value = "订单编号",required = true) @RequestParam(value = "orderNo", required=false) String orderNo,
                           @ApiParam(value = "当前页") @RequestParam(value = "pageNum",required = false) Integer pageNum,
                           @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return "请求测试成功";
    }

}
