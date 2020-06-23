package com.gg.model.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.system.domain.SysUser;
import com.gg.model.system.mapper.SysUserMapper;
import com.gg.model.system.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-06-19
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
