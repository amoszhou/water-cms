package com.water.util;

/**
  * @Author : 林吉达
  * @Description :
  * @Date : 10:53 2018/7/31
  */
public class
ResultDTO<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultDTO buildSuccessResult(Object o) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode("SUCCESS");
        resultDTO.setMessage("服务正常调用");
        resultDTO.setData(o);
        return resultDTO;
    }

    public static ResultDTO buildFailedResult(String code, String message, Object o) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        resultDTO.setData(o);
        return resultDTO;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
