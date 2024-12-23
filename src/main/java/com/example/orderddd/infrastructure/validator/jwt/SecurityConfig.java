package com.example.orderddd.infrastructure.validator.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().permitAll() // 允许所有用户访问登录
                // .anyRequest().authenticated() // 其他请求需认证
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 设置为无状态会话
                );
        // http.addFilterBefore(jwtRequestFilter(),
        // UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authentication -> authentication;
    }

}
