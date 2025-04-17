package com.mballem.curso.security.web.controller;

import org.springframework.http.ResponseEntity;
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
}