// Define o pacote da classe
package com.mballem.curso.security.service;

import java.util.List;

// Anotação para transações no banco
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mballem.curso.security.model.Perfil;
import com.mballem.curso.security.model.Usuario;
import com.mballem.curso.security.repository.Usuario.UsuarioRepository;

// Define que essa classe é um serviço gerenciado pelo Spring
@Service
public class UsuarioService implements UserDetailsService {

    // Injeta o repositório de usuários
    @Autowired
    private UsuarioRepository repository;

    // Busca um usuário pelo e-mail no banco (somente leitura)
    @Transactional(readOnly = true)
    public Usuario buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Método usado pelo Spring Security para autenticar o usuário
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = buscarPorEmail(username);

        // Se o usuário não existir, lança exceção
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }

        // Cria e retorna um objeto de autenticação com e-mail, senha e roles
        return new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(getAuthorities(usuario.getPerfis())));
    }

    // Converte a lista de perfis em um array de strings com as roles
    private String[] getAuthorities(List<Perfil> perfis) {
        String[] authorities = new String[perfis.size()];
        for (int i = 0; i < perfis.size(); i++) {
            authorities[i] = perfis.get(i).getDesc(); // ex: "ROLE_ADMIN"
        }
        return authorities;
    }
}
