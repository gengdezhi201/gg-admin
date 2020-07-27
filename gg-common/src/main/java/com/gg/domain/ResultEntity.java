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

    public void success(String msg, Object data) {
        this.code = CODE_SUCCESS;
        this.msg = msg;
        this.data = data;
    }

    public void success(String msg) {
        this.code = CODE_SUCCESS;
        this.msg = msg;
        this.data = null;
    }

    public void success(Object data) {
        this.code = CODE_SUCCESS;
        this.msg = MSG_SUCCESS;
        this.data = data;
    }

    public void fail(String msg, Object data) {
        this.code = CODE_FAIL;
        this.msg = msg;
        this.data = data;
    }

    public void fail(String msg) {
        this.code = CODE_FAIL;
        this.msg = msg;
        this.data = null;
    }

    public void fail(Object data) {
        this.code = CODE_FAIL;
        this.msg = MSG_FAIL;
        this.data = data;
    }
}
