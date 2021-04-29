package com.gg.model.system.domain.dto.query;

import com.gg.domain.PageEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统用户表 sys_role
 *
 * @author gengdz
 */
@Getter
@Setter
public class SysRoleQueryCriteria extends PageEntity implements Serializable {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 状态:1启用，2停用
     */
    private String status;

}
