package com.mballem.curso.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor // construtor vazio
@EqualsAndHashCode(callSuper = true) // inclui id da superclasse no equals/hashCode
public class Paciente extends AbstractEntity {

	// Nome único e obrigatório
	@Column(name = "nome", unique = true, nullable = false)
	private String nome;

	// Data de nascimento formatada como ISO DATE
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dtNascimento;

	// Lista de agendamentos do paciente (ignorado no JSON para evitar loops)
	@JsonIgnore
	@OneToMany(mappedBy = "paciente")
	private List<Agendamento> agendamentos;

	// Relação 1:1 com usuário (deleta usuário ao deletar paciente)
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
}