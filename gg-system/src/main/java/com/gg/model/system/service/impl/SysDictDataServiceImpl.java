package com.gg.model.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gg.model.system.domain.SysDict;
import com.gg.model.system.domain.SysDictData;
import com.gg.model.system.domain.dto.query.SysDictDataQueryCriteria;
import com.gg.model.system.mapper.SysDictDataMapper;
import com.gg.model.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据字典详情 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements ISysDictDataService {

    @Autowired
    SysDictDataMapper sysDictDataMapper;

    @Override
    public Page getSysDictDataListPage(SysDictDataQueryCriteria sysDictDataQueryCriteria) {
        QueryWrapper<SysDictData> wrapper = new QueryWrapper();
        wrapper.eq("dict_id",sysDictDataQueryCriteria.getDictId());
        return sysDictDataMapper.selectPage(new Page(sysDictDataQueryCriteria.getPageNum(),sysDictDataQueryCriteria.getPageSize()),wrapper);
    }
}
