package com.hss01248.spmvc.intercetor;

import com.hss01248.spmvc.bean.BaseNetBean;
import com.hss01248.utils.MyJson;
import com.hss01248.utils.MyLog;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class AuthInterceptor implements HandlerInterceptor {

    //需要校验的url列表
    private  static String[] urls = {
            "/test/getStandardJson.json",
            "/test/postCommonJson.json"

    };
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        //先判断该url是否为需要校验登录状态的url:遍历list,一个个去判断是否包含.
        String url = httpServletRequest.getRequestURL().toString();

        boolean letGo = true;
        for(String target : urls){
            if (url.contains(target)){
                letGo = false;
                break;
            }
        }
        if(letGo){
            return true;
        }
        HttpSession session = httpServletRequest.getSession(false);

        if(session ==null){
            return false;
        }

        String sessionId = httpServletRequest.getParameter("sessionId");
        if(StringUtils.isEmpty(sessionId)){
            httpServletResponse.setCharacterEncoding("utf-8");
            String str = MyJson.toJsonStr(new BaseNetBean(5,"unlogin",null));
            MyLog.e(str);
            httpServletResponse.getWriter().write(str);
            return false;
        }

        if(!sessionId.equalsIgnoreCase(session.getId())){
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.getWriter().write(MyJson.toJsonStr(new BaseNetBean(6,"login timeout",null)));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
