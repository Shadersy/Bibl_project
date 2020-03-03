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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
CommonAuthenticationEntryPoint commonAuthenticationEntryPoint;
CommonUserDetailsService commonUserDetailsService;


@Autowired
WebSecurityConfig(CommonAuthenticationEntryPoint commonAuthenticationEntryPoint, CommonUserDetailsService commonUserDetailsService){
    this.commonAuthenticationEntryPoint=commonAuthenticationEntryPoint;
    this.commonUserDetailsService=commonUserDetailsService;


}
    protected void configure(HttpSecurity httpSecurity) throws Exception {
httpSecurity
                .exceptionHandling().authenticationEntryPoint(commonAuthenticationEntryPoint)
                .and()
                .userDetailsService(commonUserDetailsService)
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

@Component
class CommonAuthenticationEntryPoint implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}


@Component
@Transactional
class CommonUserDetailsService implements UserDetailsService {
    UserDao userDao;
    CommonUserDetailsService(UserDao userDao){
      this.userDao=userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if(s.isEmpty()){
            throw new IllegalStateException("Login is empty");
        }

        return CommonUserDetailsService(userDao.getByLogin());
    }


    public class CommonUserDetails implements UserDetails{
        User user;
        CommonUserDetails(User user){
            this.user=user;
        }
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}