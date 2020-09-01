package com.gg.model.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.domain.ResultEntity;
import com.gg.model.security.domain.SysUserDetails;
import com.gg.model.system.domain.SysLogin;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.domain.dto.query.SysUserQueryCriteria;
import org.springframework.security.core.Authentication;

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
    Page getSysUserListPage(SysUserQueryCriteria sysUserQueryCriteria);
}
