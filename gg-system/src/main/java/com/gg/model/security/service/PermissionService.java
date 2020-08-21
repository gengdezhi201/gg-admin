package com.gg.model.security.service;

import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.security.util.SysUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;

@Service(value = "gg")
public class PermissionService {

    public Boolean check(String ...permissions){
        // 获取当前用户的所有权限
        Set<String> ggPermissions = SysUserUtil.getLoginUser().getPermissions();
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return ggPermissions.contains("admin") || Arrays.stream(permissions).anyMatch(ggPermissions::contains);
    }
}