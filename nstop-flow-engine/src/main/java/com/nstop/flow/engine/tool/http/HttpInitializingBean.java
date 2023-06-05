package com.nstop.flow.engine.tool.http;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author linziwei
 * @date 2023/1/29
 */
@Component
public class HttpInitializingBean implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("test11");

    }
}
