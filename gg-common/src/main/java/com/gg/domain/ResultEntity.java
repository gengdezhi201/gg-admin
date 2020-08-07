package com.gg.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResultEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码-成功
     */
    public static final String CODE_SUCCESS = "0";

    /**
     * 状态码-失败
     */
    public static final String CODE_FAIL = "1";

    /**
     * 消息-成功
     */
    public static final String MSG_SUCCESS = "操作成功";

    /**
     * 消息-失败
     */
    public static final String MSG_FAIL = "操作失败";

    private String code;

    private String msg;

    private Object data;

    public ResultEntity(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static ResultEntity success(String msg, Object data) {
        return new ResultEntity(CODE_SUCCESS,msg,data);
    }

    public static ResultEntity success(String msg) {
        return new ResultEntity(CODE_SUCCESS,msg,null);
    }

    public static ResultEntity success(Object data) {
        return new ResultEntity(CODE_SUCCESS,MSG_SUCCESS,data);
    }

    public static ResultEntity fail(String msg, Object data) {
        return new ResultEntity(CODE_FAIL,msg,data);
    }

    public static ResultEntity fail(String msg) {
        return new ResultEntity(CODE_FAIL,msg,null);
    }

    public static ResultEntity fail(Object data) {
        return new ResultEntity(CODE_FAIL,MSG_FAIL,data);
    }

}
