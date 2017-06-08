package com.hss01248.spmvc.intercetor;

import com.hss01248.spmvc.util.anno.FromDataNoRepeat;
import com.hss01248.utils.MyJson;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/8 0008.
 */
public class FormDataRepeatInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            FromDataNoRepeat annotation = method.getAnnotation(FromDataNoRepeat.class);
            if (annotation != null) {
                return !repeatDataValidator(request);
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 验证同一个url数据是否相同提交  ,相同返回true
     * @param httpServletRequest
     * @return
     */
    public boolean repeatDataValidator(HttpServletRequest httpServletRequest) {
        String params= MyJson.toJsonStr(httpServletRequest.getParameterMap());
        String url=httpServletRequest.getRequestURI();
        Map<String,String> map=new HashMap<String,String>();
        map.put(url, params);
        String nowUrlParams=MyJson.toJsonStr(map);//
        HttpSession session = httpServletRequest.getSession(false);
        if(session==null){
            return false;
        }
        Object preUrlParams=session.getAttribute("repeatData");
        if(preUrlParams==null){//如果上一个数据为null,表示还没有访问页面
            httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
            return false;
        } else{//否则，已经访问过页面
            if(preUrlParams.toString().equals(nowUrlParams)) {//如果上次url+数据和本次url+数据相同，则表示重复添加数据
                return true;
            } else {//如果上次 url+数据 和本次url加数据不同，则不是重复提交
                httpServletRequest.getSession().setAttribute("repeatData", nowUrlParams);
                return false;
            }

        }
    }
}
