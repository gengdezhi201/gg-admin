package com.gg.model.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.gg.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@Getter
@Setter
public class SysRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 名称
     */
    private String name;

    /**
     * 角色级别
     */
    private Integer level;

    /**
     * 描述
     */
    private String description;

    /**
     * 数据权限
     */
    private String dataScope;

}
