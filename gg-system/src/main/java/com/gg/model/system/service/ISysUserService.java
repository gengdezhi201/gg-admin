package com.gg.model.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.domain.ResultEntity;
import com.gg.model.system.domain.SysLogin;
import com.gg.model.system.domain.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-06-19
 */
public interface ISysUserService extends IService<SysUser> {

    ResultEntity login(SysLogin sysLogin);

    ResultEntity getUserInfo(HttpServletRequest request);
}
