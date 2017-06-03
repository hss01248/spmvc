package com.hss01248.spmvc.bean;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/3.
 */
public class ErrorMsg {

    static {
        initMap();
    }

    private static void initMap() {
        map = new HashMap();
        map.put(ErrorCode.OK,"请求成功");
        map.put(ErrorCode.UNLOGIN,"您还没有登录");
        map.put(ErrorCode.TOKEN_INVALID,"登录状态已过期,请重新登录");
        map.put(ErrorCode.SERVER_ERROR,"服务器出错");
        map.put(ErrorCode.UNFOUND,"没有找到相应的资源");
    }

    private static HashMap<Integer,String> map ;

    public static String getMsg(int errorCode){
       return map.get(errorCode);
    }
}
