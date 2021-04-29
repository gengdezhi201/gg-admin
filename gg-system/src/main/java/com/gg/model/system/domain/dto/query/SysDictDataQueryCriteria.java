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
public class SysDictDataQueryCriteria extends PageEntity implements Serializable {

    private Integer dictId;
}
