package com.hss01248.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class MyLog {

    private static Logger logger = LoggerFactory.getLogger(MyLog.class);

    public static void d(Object obj){
        logger.debug(str(obj));
    }
    public static void e(Object obj){

        logger.error(str(obj));
    }

    private static String str(Object obj) {
        return MyJson.toJsonStr(obj);
    }

    public static void i(Object obj){
        logger.info(str(obj));
    }

}
