package com.gg.model.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.system.domain.SysDict;
import com.gg.model.system.domain.dto.query.SysDictQueryCriteria;
import com.gg.model.system.mapper.SysDictMapper;
import com.gg.model.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Autowired
    SysDictMapper sysDictMapper;
    @Override
    public Page getSysDictListPage(SysDictQueryCriteria sysDictQueryCriteria) {
        QueryWrapper<SysDict> wrapper = new QueryWrapper();
        wrapper.
                like(sysDictQueryCriteria.getDictName()!=null,"dict_name",sysDictQueryCriteria.getDictName())
                .or()
                .like(sysDictQueryCriteria.getDictName()!=null,"description",sysDictQueryCriteria.getDictName());
        return sysDictMapper.selectPage(new Page(sysDictQueryCriteria.getPageNum(),sysDictQueryCriteria.getPageSize()),wrapper);
    }
}
