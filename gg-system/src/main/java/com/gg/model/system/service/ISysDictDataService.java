package com.gg.model.system.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.model.system.domain.SysDictData;
import com.gg.model.system.domain.dto.query.SysDictDataQueryCriteria;

/**
 * <p>
 * 数据字典详情 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface ISysDictDataService extends IService<SysDictData> {

    Page getSysDictDataListPage(SysDictDataQueryCriteria sysDictDataQueryCriteria);
}
