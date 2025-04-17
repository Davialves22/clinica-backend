package com.mballem.curso.security.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    // retornar JSON
    @GetMapping({"/", "/home"})
    public ResponseEntity<Object> home() {

        return ResponseEntity.ok().body(new Object() {
            public String message = "Bem-vindo à home!";
        });
    }

    @GetMapping({"/", "/login"})
    public ResponseEntity<Object> login() {

        return ResponseEntity.ok().body(new Object() {
            public String message = "Bem-vindo à login!";
        });
    }

    @GetMapping({"/login-error"})
    public ResponseEntity<Object> loginError() {

        return ResponseEntity.ok().body(new Object() {
            public String LoginError (ModelMap model ){
                model.addAttribute("alerta", "erro");
                model.addAttribute("titulo", "Credenciais invalidas");
                model.addAttribute("texto", "Login ou senha incorretos,tente novamente");
                model.addAttribute("subtexto", "Acesso permitido apenas para cadastros ativados");
                return "login";
            };
        });
    }
}