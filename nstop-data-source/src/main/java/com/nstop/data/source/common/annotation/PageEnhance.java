package com.nstop.data.source.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: origindoris
 * @Title: PageEnhance
 * @Description:
 * @date: 2022/11/18 10:52
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface PageEnhance {
}
