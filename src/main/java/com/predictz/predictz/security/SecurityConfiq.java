package com.predictz.predictz.security;

import com.predictz.predictz.model.ROLE;
import com.predictz.predictz.service.solidpay.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiq extends WebSecurityConfigurerAdapter {
    @Autowired
    private JWTFilter filter;
    @Autowired
    private AppUserService appUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/faqs/**").permitAll()
                .antMatchers("/users/**").permitAll()
                .antMatchers("/interact/**").permitAll()
                .antMatchers(HttpMethod.GET, "/predictions/**").permitAll()
                .antMatchers(HttpMethod.GET, "/subscriptions/**").permitAll()
                .antMatchers("/testimonials/**").permitAll()
                .antMatchers(HttpMethod.GET, "/settings/**").permitAll()
                .antMatchers(HttpMethod.POST,"/settings/**").hasRole(ROLE.ADMIN.name())
                .antMatchers("*").permitAll()
//                .anyRequest().authenticated()
                .and()
                .userDetailsService(appUserService)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                );

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
