package com.gg.model.system.rest;

import com.gg.domain.ResultEntity;
import com.gg.model.system.domain.SysLogin;
import com.gg.model.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SysLoginController {

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/login")
    public ResultEntity login(@Validated @RequestBody SysLogin sysLogin){
        return sysUserService.login(sysLogin);
    }

    @GetMapping("/getUserInfo")
    @PreAuthorize("true")
    public ResultEntity getUserInfo(HttpServletRequest request){
        return sysUserService.getUserInfo(request);
    }
}
