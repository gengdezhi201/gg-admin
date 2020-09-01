package com.gg.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageEntity {

    /**
     * 总数
     */
    private long total;

    /**
     * 每页显示条数
     */
    private long pageSize;

    /**
     * 当前页数
     */
    private long pageNum;
}
