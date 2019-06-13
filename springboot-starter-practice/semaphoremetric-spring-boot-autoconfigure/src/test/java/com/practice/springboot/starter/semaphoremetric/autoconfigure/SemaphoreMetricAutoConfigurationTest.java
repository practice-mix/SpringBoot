package com.practice.springboot.starter.semaphoremetric.autoconfigure;

import com.netflix.hystrix.HystrixCommand;
import com.practice.springboot.starter.semaphoremetric.core.SemaphoreMetricProducer;
import com.practice.springboot.starter.semaphoremetric.core.SemaphoreMetricScheduler;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.test.context.FilteredClassLoader;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * @author Luo Bao Ding
 * @since 2018/12/11
 */
public class SemaphoreMetricAutoConfigurationTest {
    private final ConditionEvaluationReportLoggingListener loggingListener = new ConditionEvaluationReportLoggingListener(LogLevel.INFO);
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(SemaphoreMetricAutoConfiguration.class)).withInitializer(loggingListener);


    @Test
    public void defaultServiceBacksOff() {
        this.contextRunner.run(context -> {
            Assertions.assertThat(context).hasSingleBean(SemaphoreMetricScheduler.class);
            Assertions.assertThat(context).hasSingleBean(SemaphoreMetricProducer.class);

        });

    }

    @Test
    public void serviceIsIgnoredIfDisabled() {
        this.contextRunner.withPropertyValues("metric.semaphore.enabled=false").run((context) -> {
            Assertions.assertThat(context).doesNotHaveBean(SemaphoreMetricScheduler.class);
            Assertions.assertThat(context).doesNotHaveBean(SemaphoreMetricProducer.class);
        });
    }

    @Test
    public void serviceIsIgnoredIfLibraryIsNotPresent() {
        this.contextRunner.withClassLoader(new FilteredClassLoader(HystrixCommand.class))
                .run(context -> {
                    Assertions.assertThat(context).doesNotHaveBean(SemaphoreMetricScheduler.class);
                    Assertions.assertThat(context).doesNotHaveBean(SemaphoreMetricProducer.class);
                });
    }
}