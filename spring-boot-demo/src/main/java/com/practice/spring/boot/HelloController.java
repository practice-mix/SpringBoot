package com.practice.spring.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloController {

    @Value("18")
    private Integer age;

    @Value("${server.port}")
    private Integer port;

    @Value("${server.port:1111}")
    private Integer port2;

    @Value("${app.username:}")
    private String username;

    @Value("${app.username:john}")
    private String username2;


    public HelloController() {
        System.out.println("........constructing");
    }

    @RequestMapping("hello")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);
        return requestURI;
    }

    @RequestMapping("/port")
    public Integer port() {
        switch (port) {
            case 1:
                System.out.println(1);
            case 2:
                System.out.println(2);
            default:
                System.out.println("others");
        }
        return port;
    }


}
