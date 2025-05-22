package com.mballem.curso.security.service.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mballem.curso.security.model.Usuario;
import com.mballem.curso.security.repository.Usuario.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }
}
