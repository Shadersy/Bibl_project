package ru.gvozdilin.bibl.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.gvozdilin.bibl.dao.UserDao;
import ru.gvozdilin.bibl.entity.User;
import ru.gvozdilin.bibl.service.MyUserDetailsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
CommonAuthenticationEntryPoint commonAuthenticationEntryPoint;
MyUserDetailsService myUserDetailsService;


    protected void configure(HttpSecurity httpSecurity) throws Exception {
httpSecurity
                .exceptionHandling().authenticationEntryPoint(commonAuthenticationEntryPoint)
                .and()
                .userDetailsService(myUserDetailsService)
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/**").hasAnyAuthority("ADMIN")
                .anyRequest().authenticated().and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .and()
                .logout()
                .deleteCookies("remove").invalidateHttpSession(false);
    }



        }





