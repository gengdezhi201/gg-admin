package com.gg.model.system.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gg.domain.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户表 sys_user
 *
 * @author gengdz
 */
@Getter
@Setter

public class SysUserDto extends BaseDto implements Serializable {

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 最近一次的旧密码
     */
    private String lastPassword;

    /**
     * 修改密码时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date passwordChangeTime;

    /**
     * 头像地址
     */
    private String avatarPath;

    /**
     * 性别:1男,2女
     */
    private char gender;

    /**
     * 手机
     */
//    @NotBlank(message = "手机号不能为空")
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态:1启用，2停用
     */
    private String status;

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", deptId=" + deptId +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", lastPassword='" + lastPassword + '\'' +
                ", passwordChangeTime=" + passwordChangeTime +
                ", avatarPath='" + avatarPath + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createUserName='" + super.getCreateUserName() + '\'' +
                ", createTime='" + super.getCreateTime() + '\'' +
                ", updateUserName='" + super.getUpdateUserName() + '\'' +
                ", updateTime='" + super.getUpdateTime() + '\'' +
                '}';
    }
}
