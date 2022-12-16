package com.nstop.common.interceptor;


import com.nstop.common.model.UserInfo;

/**
 * @author: origindoris
 * @Title: UserThreadLocal
 * @Description:
 * @date: 2022/8/26 16:15
 */
public class UserInfoThreadLocal {

    private static final ThreadLocal<UserInfo> LOCAL = new ThreadLocal<UserInfo>();

    public static void set(UserInfo user){
        LOCAL.set(user);
    }

    public static UserInfo get(){
        // todo
        UserInfo userInfo = LOCAL.get();
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setName("谢浩哲");
            userInfo.setNick("桓温");
            userInfo.setEmpId("4929");
            userInfo.setTenantCode("test");
            UserInfoThreadLocal.set(userInfo);
            set(userInfo);
        }
        return userInfo;
    }

    public static void remove(){
        LOCAL.remove();
    }
}
