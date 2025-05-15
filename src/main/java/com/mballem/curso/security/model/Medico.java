package com.mballem.curso.security.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "medicos")
@Data
@NoArgsConstructor // Construtor vazio
@EqualsAndHashCode(callSuper = true) // Usa id da superclasse no equals e hashCode
public class Medico extends AbstractEntity {

	// Nome único e obrigatório
	@Column(name = "nome", unique = true, nullable = false)
	private String nome;

	// CRM único e obrigatório (registro médico)
	@Column(name = "crm", unique = true, nullable = false)
	private Integer crm;

	// Data de inscrição formatada como ISO DATE
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_inscricao", nullable = false)
	private LocalDate dtInscricao;

	// Lista de especialidades associadas (evita recursão no JSON)
	@JsonIgnore
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "medicos_tem_especialidades", joinColumns = @JoinColumn(name = "id_medico", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_especialidade", referencedColumnName = "id"))
	private Set<Especialidade> especialidades;

	// Lista de agendamentos do médico (evita recursão no JSON)
	@JsonIgnore
	@OneToMany(mappedBy = "medico")
	private List<Agendamento> agendamentos;

	// Relação 1:1 com usuário (se apagar médico, apaga usuário também)
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	// Construtor com id para facilitar instância por id
	public Medico(Long id) {
		super.setId(id);
	}

	// Construtor com usuário
	public Medico(Usuario usuario) {
		this.usuario = usuario;
	}
}
