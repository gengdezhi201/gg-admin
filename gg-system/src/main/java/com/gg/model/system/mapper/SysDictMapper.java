package com.gg.model.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gg.model.system.domain.SysDict;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> selectDictDataBydictId();
}
