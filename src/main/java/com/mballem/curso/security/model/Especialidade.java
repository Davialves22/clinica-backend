package com.mballem.curso.security.model;

import java.util.List;
import jakarta.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "especialidades", indexes = {
		@Index(name = "idx_especialidade_titulo", columnList = "titulo") // índice para busca por título
})
@Data
@NoArgsConstructor // construtor vazio
@EqualsAndHashCode(callSuper = true) // inclui id da superclasse no equals e hashCode
public class Especialidade extends AbstractEntity {

	// Título único e obrigatório
	@Column(name = "titulo", unique = true, nullable = false)
	private String titulo;

	// Descrição, tipo texto longo
	@Column(name = "descricao", columnDefinition = "TEXT")
	private String descricao;

	// Muitos médicos podem ter muitas especialidades (tabela intermediária)
	@ManyToMany
	@JoinTable(name = "medicos_tem_especialidades", joinColumns = @JoinColumn(name = "id_especialidade", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id"))
	private List<Medico> medicos;
}