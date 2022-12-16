package com.nstop.data.source.common.aspect;

import com.nstop.common.model.BaseQuery;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author: origindoris
 * @Title: PageEnhanceAspect
 * @Description:
 * @date: 2022/11/18 10:53
 */
@Aspect
@Component
public class PageEnhanceAspect {

    @Pointcut("@annotation(com.nstop.data.source.common.annotation.PageEnhance)")
    public void methodPointcut() {
    }


    @Around("methodPointcut()")
    public Object enhance(ProceedingJoinPoint call) throws Throwable  {
        Object[] args = call.getArgs();
        BaseQuery baseQuery = null;
        for (Object arg : args) {
            if (arg instanceof BaseQuery) {
                baseQuery = (BaseQuery) arg;
                break;
            }
        }
        if (baseQuery == null) {
          return call.proceed();
        }
        if (baseQuery.getPage() > 0) {
            baseQuery.setPage(baseQuery.getPage() - 1);
        }
        Object proceed = call.proceed();
        if (proceed instanceof Page) {
            Page result = (Page) proceed;
            AbstractPageRequest pageable = (AbstractPageRequest)result.getPageable();
            Field page = AbstractPageRequest.class.getDeclaredField("page");
            page.setAccessible(true);
            page.setInt(pageable, pageable.getPageNumber() + 1 & ~Modifier.FINAL);
        }
        return proceed;
    }
}
