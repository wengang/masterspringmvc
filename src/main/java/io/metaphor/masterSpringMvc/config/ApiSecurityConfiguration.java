package io.metaphor.masterSpringMvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(1)
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user1").roles("USER").and()
                .withUser("admin").password("admin").roles("USER","ADMIN","ACTUATOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/api/**")
                .httpBasic()
                .and()
                .headers().cacheControl().disable()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login","/logout").permitAll()
                .antMatchers(HttpMethod.GET,"/api/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN")
                .anyRequest().authenticated();

    }
}
