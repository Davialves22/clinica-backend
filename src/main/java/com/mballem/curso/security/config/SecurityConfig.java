package com.mballem.curso.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.web.servlet.config.annotation.*;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() // habilitar o CORS
                .and()
                .csrf().disable() // desativa CSRF usando o fetch()
                .authorizeRequests()
                .antMatchers("/", "/home", "/login", "/login-error", "/image/**", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/login") // recebe POST do React
                .successHandler((request, response, authentication) -> {
                    response.setStatus(200); // devolve OK pro React
                })
                .failureHandler((request, response, exception) -> {
                    response.sendError(401, "Credenciais inválidas"); // devolve erro para o React
                })
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(200); // avisa sucesso no logout
                });
    }


}