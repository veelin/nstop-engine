package com.nstop.common.interceptor;


import com.nstop.common.model.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author: origindoris
 * @Title: UserInfoInterceptor
 * @Description:
 * @date: 2022/8/26 16:18
 */
public class UserInfoInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler) throws Exception {
        // todo 获取用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setName("谢浩哲");
        userInfo.setNick("桓温");
        userInfo.setTenantCode("doris");
        userInfo.setEmpId("4929");
        UserInfoThreadLocal.set(userInfo);
        return true;
    }

    @Override
    public void afterCompletion(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserInfoThreadLocal.remove();
    }
}
