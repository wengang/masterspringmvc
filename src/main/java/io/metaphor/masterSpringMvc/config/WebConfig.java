package io.metaphor.masterSpringMvc.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Filter;


@Configuration
public class WebConfig {
    @Bean
    public Filter etagFilter() {
        return new ShallowEtagHeaderFilter();
    }
//    @Bean
//    public FilterRegistrationBean etagFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(etagFilter());
//        registration.addUrlPatterns("/*");
////        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("EtagFilter");
////        registration.setOrder(6);
//        return registration;
//    }
}
