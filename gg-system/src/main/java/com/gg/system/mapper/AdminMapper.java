package com.gg.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.gg.system.domain.Admin;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {
    List<Admin> getAdmin();
}
