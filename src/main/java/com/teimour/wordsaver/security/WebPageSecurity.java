package com.teimour.wordsaver.security;

import com.teimour.wordsaver.security.user.Authority;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author kebritam
 * Project word-saver
 * Created on 21/11/2020
 */

@EnableWebSecurity
public class WebPageSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/home", "/login").permitAll()
                .antMatchers("**/edit","**/new").hasAuthority(Authority.ROLE_ADMIN.name())
                .antMatchers("**/show","/","/index").hasAnyAuthority(Authority.ROLE_ADMIN.name(), Authority.ROLE_USER.name())
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login").permitAll()
            .and()
            .logout()
                .permitAll()
            .and()
            .httpBasic();
    }
}
