//package com.gg.model.system.rest;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.gg.model.security.domain.JwtProperties;
//import com.gg.model.security.domain.SysUserDetails;
//import com.gg.model.security.util.SysUserUtil;
//import com.gg.model.system.domain.SysUser;
//import com.gg.model.system.mapper.SysUserMapper;
//import com.gg.model.system.service.ISysUserService;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping(value = "sysUser")
//public class SysUserControllerbf {
//
//    @Autowired
//    private SysUserMapper sysUserMapper;
//
//    @Autowired
//    private ISysUserService sysUserService;
//
//    @Autowired
//    JwtProperties jwtProperties;
//
//    @GetMapping("/login")
//    public SysUserDetails login(String username, String password){
////        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name","admin"));
//        return sysUserService.login(username,password);
//    }
//
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping("/demo")
//    public SysUserDetails demo(){
//        SysUser sysUser = sysUserMapper.selectOne(new QueryWrapper<SysUser>().eq("user_name","admin"));
//        if (sysUser != null) {
//            System.out.println(sysUser.toString());
//            System.out.println(sysUser.getCreateTime());
//        }
//        System.out.println("--------------"+ SysUserUtil.getLoginUser());
//        return SysUserUtil.getLoginUser();
//    }
//
//    @ApiOperation(value = "获取新的订单信息")
//    @GetMapping(value = "order")
//    @PreAuthorize("hasRole('ROLE_ADMIN1')")
//    public String getOrder(@ApiParam(value = "订单编号",required = true) @RequestParam(value = "orderNo", required=false) String orderNo,
//                           @ApiParam(value = "当前页") @RequestParam(value = "pageNum",required = false) Integer pageNum,
//                           @ApiParam(value = "每页显示数量") @RequestParam(value = "pageSize",required = false) Integer pageSize){
//        return "请求测试成功";
//    }
//
//    @PostMapping(value = "")
//    public String add(@RequestBody SysUser sysUser){
//        return "请求测试成功";
//    }
//
//
//}
