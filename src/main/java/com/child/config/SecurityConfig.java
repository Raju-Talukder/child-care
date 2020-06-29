package com.child.config;

import com.child.principle.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

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
        http.csrf()
                .disable()
                .authorizeRequests()
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
                .and()
                .formLogin()
               .loginPage("/login")
                .defaultSuccessUrl("/").and().logout();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
