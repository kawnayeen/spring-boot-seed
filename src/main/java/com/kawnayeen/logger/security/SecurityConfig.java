package com.kawnayeen.logger.security;

import com.kawnayeen.logger.model.RoleConstant;
import com.kawnayeen.logger.security.basic.auth.BasicAuthProvider;
import com.kawnayeen.logger.security.token.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by kawnayeen on 1/2/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private BasicAuthProvider basicAuthProvider;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(basicAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
//        http.csrf().disable()
//                .antMatcher("/api/**")
//                .authorizeRequests()
//                .anyRequest()
//                .hasRole(RoleConstant.USER)
//                .and()
//                .httpBasic()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // @formatter:on

        http
                .csrf().disable().httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/**").hasRole(RoleConstant.USER);

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
