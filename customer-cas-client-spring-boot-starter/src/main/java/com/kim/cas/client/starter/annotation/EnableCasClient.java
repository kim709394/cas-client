package com.kim.cas.client.starter.annotation;


import net.unicon.cas.client.configuration.CasClientConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author huangjie
 * @description
 * @date 2020/5/20
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(CasClientConfiguration.class)
public @interface EnableCasClient {



}
