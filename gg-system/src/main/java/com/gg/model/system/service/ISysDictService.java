package com.gg.model.system.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gg.model.system.domain.SysDict;
import com.gg.model.system.domain.dto.query.SysDictQueryCriteria;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
public interface ISysDictService extends IService<SysDict> {

    Page getSysDictListPage(SysDictQueryCriteria sysDictQueryCriteria);
}
