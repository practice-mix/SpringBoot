package com.practice.springboot.starter.semaphoremetric.autoconfigure;

import com.netflix.hystrix.HystrixCommand;
import com.practice.springboot.starter.semaphoremetric.core.EnableSemaphoreMetric;
import com.practice.springboot.starter.semaphoremetric.core.SemaphoreMetricProducer;
import com.practice.springboot.starter.semaphoremetric.core.SemaphoreMetricScheduler;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.autoconfigure.task.TaskSchedulingAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Luo Bao Ding
 * @since 2018/12/10
 */
@Configuration
@EnableConfigurationProperties(SemaphoreMetricProperties.class)
@ConditionalOnClass(HystrixCommand.class)
@ConditionalOnProperty(name = "metric.semaphore.enabled", havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter({TaskSchedulingAutoConfiguration.class, TaskExecutionAutoConfiguration.class})
@EnableSemaphoreMetric
public class SemaphoreMetricAutoConfiguration {

    @Bean
    public SemaphoreMetricScheduler semaphoreMetricScheduler(SemaphoreMetricProducer semaphoreMetricProducer) {
        return new SemaphoreMetricScheduler(semaphoreMetricProducer);
    }

    @Bean
    public SemaphoreMetricProducer semaphoreMetricProducer() {
        return new SemaphoreMetricProducer();
    }
}
