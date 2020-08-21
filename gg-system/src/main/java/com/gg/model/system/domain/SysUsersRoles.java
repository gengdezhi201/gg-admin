package com.gg.model.system.domain;

import java.io.Serializable;

import com.gg.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色关联
 * </p>
 *
 * @author jobob
 * @since 2020-07-27
 */
@Getter
@Setter
public class SysUsersRoles implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;


}
