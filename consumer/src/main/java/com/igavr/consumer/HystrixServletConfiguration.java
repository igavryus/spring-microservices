package com.igavr.consumer;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@Configuration
public class HystrixServletConfiguration {

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> jerseyServlet() {
        ServletRegistrationBean<HystrixMetricsStreamServlet> registration = new ServletRegistrationBean<HystrixMetricsStreamServlet>(new HystrixMetricsStreamServlet(), "/hystrix.stream");
        return registration;
    }

}
