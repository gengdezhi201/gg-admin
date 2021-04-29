package com.gg.model.system.domain.dto.query;

import com.gg.domain.PageEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 数据字典表 sys_user
 *
 * @author gengdz
 */
@Getter
@Setter
public class SysDictQueryCriteria extends PageEntity implements Serializable {

    /**
     * 字典名称
     */
    private String dictName;

    /**
     * 字典描述
     */
    private String description;

}
