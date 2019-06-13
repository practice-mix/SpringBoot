package com.practice.springboot.starter.semaphoremetric.core;

import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

/**
 * @author Luo Bao Ding
 * @since 2018/12/10
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableScheduling
@Inherited
public @interface EnableSemaphoreMetric {
}


