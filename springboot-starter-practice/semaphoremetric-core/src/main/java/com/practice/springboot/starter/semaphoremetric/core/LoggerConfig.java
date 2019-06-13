package com.practice.springboot.starter.semaphoremetric.core;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * @author Luo Bao Ding
 * @since 2018/12/10
 */
public class LoggerConfig {

    public static void init() {
        URL resource = LoggerConfig.class.getResource("logback-semaphoremetric.xml");
        configLogback(resource);
    }


    public static void configLogback(URL url) {
        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
        LoggerContext loggerContext=(LoggerContext)iLoggerFactory;
        JoranConfigurator joranConfigurator=new JoranConfigurator();
        joranConfigurator.setContext(loggerContext);
        try {
            joranConfigurator.doConfigure(url);
        } catch (JoranException ignored) {
        }

        StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);

    }
}
