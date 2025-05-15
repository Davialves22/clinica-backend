package com.mballem.curso.security.model;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "perfis")
@Data
@NoArgsConstructor // construtor vazio
@EqualsAndHashCode(callSuper = true) // inclui id da superclasse no equals/hashCode
public class Perfil extends AbstractEntity {

	// Descrição única e obrigatória
	@Column(name = "descricao", nullable = false, unique = true)
	private String desc;

	// Construtor com id (herdado)
	public Perfil(Long id) {
		super.setId(id);
	}
}
