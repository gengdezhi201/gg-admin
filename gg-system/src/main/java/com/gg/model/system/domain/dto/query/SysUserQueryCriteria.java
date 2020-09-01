package com.gg.model.system.domain.dto.query;

import com.gg.domain.PageEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 系统用户表 sys_user
 *
 * @author gengdz
 */
@Getter
@Setter
public class SysUserQueryCriteria extends PageEntity implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 状态:1启用，2停用
     */
    private String status;

}
