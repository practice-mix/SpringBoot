package com.practice.springboot.starter.semaphoremetric.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Luo Bao Ding
 * @since 2018/12/10
 */
@ConfigurationProperties("metric.semaphore")
public class SemaphoreMetricProperties {

    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
