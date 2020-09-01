package com.gg.model.system.rest;

import com.gg.model.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-09-01
 */
@RestController
@RequestMapping("/model/system/sys-dict")
public class SysDictController{

    @Autowired
    ISysDictService sysDictService;
}
