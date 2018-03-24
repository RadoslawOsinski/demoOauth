package com.example.soc.demo.connection;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by Radosław Osiński
 */
@Configuration
public class SecConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login/authenticate")
                    .failureUrl("/login?param.error=bad_credentials")
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID")
                .and()
                    .authorizeRequests()
                    .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                    .permitAll()
                .and()
                    .rememberMe()
                .and()
                    .apply(new SpringSocialConfigurer());
    }

}
