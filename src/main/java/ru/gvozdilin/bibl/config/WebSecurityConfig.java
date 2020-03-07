package ru.gvozdilin.bibl.config;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.gvozdilin.bibl.service.MyUserDetailsService;


@Configuration

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CommonAuthenticationEntryPoint commonAuthenticationEntryPoint;

    @Autowired
    MyUserDetailsService myUserDetailsService;


        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity .authorizeRequests()
                .antMatchers("/securityNone")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .userDetailsService(myUserDetailsService);

//                .exceptionHandling().authenticationEntryPoint(commonAuthenticationEntryPoint)
//                .and()
//                .userDetailsService(myUserDetailsService)
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/api/**").hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated().and().csrf().disable()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("login")
//                .passwordParameter("password")
//                .and()
//                .logout()
//                .deleteCookies("remove").invalidateHttpSession(false);

            httpSecurity.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }
}









