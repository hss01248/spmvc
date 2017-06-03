package com.hss01248.spmvc.bean;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class BaseNetBean<T> {

    public void setCode(int code) {
        this.code = code;
    }

    private int code = ErrorCode.OK;
    private String msg = ErrorMsg.getMsg(ErrorCode.OK);
    private T data;

    public BaseNetBean(){

    }




    public BaseNetBean(int code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseNetBean(T data){
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public BaseNetBean setCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
