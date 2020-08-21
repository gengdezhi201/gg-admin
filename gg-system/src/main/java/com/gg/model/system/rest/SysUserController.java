package com.gg.model.system.rest;

import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.JwtProperties;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    JwtProperties jwtProperties;

    @PostMapping
    public ResultEntity add(@RequestBody SysUser sysUser){
        if(sysUserService.save(sysUser)){
            return ResultEntity.success("添加成功");
        }else{
            return ResultEntity.fail("添加失败");
        }
    }


}
