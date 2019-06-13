package com.practice.springboot.starter.semaphoremetric.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Luo Bao Ding
 * @since 2018/12/10
 */
public class SemaphoreMetricScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SemaphoreMetricScheduler.class);

    private final SemaphoreMetricProducer semaphoreMetricProducer;

    public SemaphoreMetricScheduler(SemaphoreMetricProducer semaphoreMetricProducer) {
        this.semaphoreMetricProducer = semaphoreMetricProducer;
    }

    @Scheduled(fixedRate = 1000)
    public void metricSemaphore() {
        try {
            semaphoreMetricProducer.logSemaphore();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            LOGGER.error("log semaphore metric exception", e);
        }
    }

}
