package com.mballem.curso.security.model;

import java.io.Serializable;
import jakarta.persistence.*; // Atualizado de javax para jakarta

import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@MappedSuperclass
@Data // Gera getters, setters, toString, equals e hashCode automaticamente
@NoArgsConstructor // Gera construtor sem argumentos
public abstract class AbstractEntity implements Serializable {

    // Classe genérica para entidades que terão um ID compartilhado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Long id;

    // Verifica se o ID é nulo (entidade nova)
    public boolean hasNotId() {
        return id == null;
    }

    // Verifica se o ID não é nulo (entidade existente)
    public boolean hasId() {
        return id != null;
    }
}