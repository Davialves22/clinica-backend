package com.mballem.curso.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mballem.curso.security.service.UsuarioService;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService service;

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

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder()); // usa o serviço de usuários para autenticação
    }
}
