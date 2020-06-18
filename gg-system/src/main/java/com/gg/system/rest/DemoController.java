package com.gg.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gg.system.domain.Admin;
import com.gg.system.mapper.AdminMapper;
import com.gg.system.service.AdminService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    AdminService adminService;

    @GetMapping("/demo")
    public List<Admin> demo(){
        QueryWrapper<Admin> wrapper = new QueryWrapper();
        List<Admin> list = adminService.list();
        return list;
    }

    @ApiOperation(value = "获取新的订单信息")
    @GetMapping(value = "order")
    public String getOrder(@ApiParam(value = "订单编号",required = true) @RequestParam(value = "orderNo", required=false) String orderNo,
                           @ApiParam(value = "当前页") @RequestParam(value = "pageNum",required = false) Integer pageNum,
                           @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return "请求测试成功";
    }
}
