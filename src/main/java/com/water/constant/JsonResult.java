package com.water.constant;

/**
 * Created by Administrator on 2017/4/5.
 */
public class JsonResult {

    private boolean result;
    private Object data;
    private String msg;

    public JsonResult(boolean result, Object data) {
        this.result = result;
        this.data = data;
    }

    public JsonResult(boolean result, String msg) {
        this.result = result;
        this.msg = msg;
    }

    public JsonResult(boolean result) {
        this.result = result;
    }

    public JsonResult() {
    }

    public boolean isResult() {
        return result;
    }

    public JsonResult setResult(boolean result) {
        this.result = result;
        return this;
    }

    public Object getData() {
        return data;
    }

    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
