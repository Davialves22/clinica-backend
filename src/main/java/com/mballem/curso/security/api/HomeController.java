package com.mballem.curso.security.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // Retorna JSON para / e /home
    @GetMapping({ "/", "/home" })
    public ResponseEntity<Object> home() {
        return ResponseEntity.ok().body(new Object() {
            public String message = "Bem-vindo à home!";
        });
    }

    // Retorna JSON para /login
    @GetMapping("/login")
    public ResponseEntity<Object> login() {
        return ResponseEntity.ok().body(new Object() {
            public String message = "Bem-vindo à página de login!";
        });
    }

    // Retorna JSON para /login-error
    @GetMapping("/login-error")
    public ResponseEntity<Object> loginError() {
        return ResponseEntity.status(401).body(new Object() {
            public String alerta = "erro";
            public String titulo = "Credenciais inválidas";
            public String texto = "Login ou senha incorretos, tente novamente";
            public String subtexto = "Acesso permitido apenas para cadastros ativados";
        });
    }
}
