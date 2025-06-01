package com.mballem.curso.security.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mballem.curso.security.model.enums.PerfilTipo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "usuarios", indexes = {
    @Index(name = "idx_usuario_email", columnList = "email")})

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // inclui id da superclasse no equals e hashCode
public class Usuario extends AbstractEntity {

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // Senha omitida do JSON
    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuarios_tem_perfis", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "perfil_id", referencedColumnName = "id"))
    private List<Perfil> perfis;

    // Indica se o usuário está ativo (true/false)
    @Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ativo;

    // Código verificador para validações (6 caracteres)
    @Column(name = "codigo_verificador", length = 6)
    private String codigoVerificador;

    // Construtor com id para facilitar criação parcial
    public Usuario(Long id) {
        super.setId(id);
    }

    // Construtor só com email
    public Usuario(String email) {
        this.email = email;
    }

    // Adiciona um perfil ao usuário (cria novo Perfil a partir do código)
    public void addPerfil(PerfilTipo tipo) {
        if (this.perfis == null) {
            this.perfis = new ArrayList<>();
        }
        this.perfis.add(new Perfil(tipo.getCod()));
    }
}