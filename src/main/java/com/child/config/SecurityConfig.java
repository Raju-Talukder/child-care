package com.child.config;

import com.child.principle.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private LoginSuccessHandler successHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/","/admin/add/contact","/admin/list/employee",
                        "/admin/child/list","/admin/list/user","/admin/child/request",
                        "admin/request/user","/admin/child/add","/admin/add/user",
                        "/admin/add/employee","/admin/add/contact","/admin/add/packages",
                        "/admin/add/team","/admin/attendance","/admin/assign-food").hasAuthority("ADMIN")
                .antMatchers("/user/","/user/child-add","/user/child-profile",
                        "/user/daily-report").hasAuthority("USER")
                .antMatchers("/","/contact","/about","/packages","/gallery",
                        "/sign-up/**", "/verify-code/**","/login/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable().formLogin()
               .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(successHandler)
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").deleteCookies("remember-me").permitAll()
                .and()
                .rememberMe();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
