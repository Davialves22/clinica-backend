package com.mballem.curso.security.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "agendamentos")
@Data
@NoArgsConstructor // Construtor vazio
@EqualsAndHashCode(callSuper = true) // equals e hashCode incluem id da classe pai
public class Agendamento extends AbstractEntity {

	// Especialidade relacionada
	@ManyToOne
	@JoinColumn(name = "id_especialidade")
	private Especialidade especialidade;

	// Médico relacionado
	@ManyToOne
	@JoinColumn(name = "id_medico")
	private Medico medico;

	// Paciente relacionado
	@ManyToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	// Horário relacionado
	@ManyToOne
	@JoinColumn(name = "id_horario")
	private Horario horario;

	// Data da consulta (formato data)
	@Column(name = "data_consulta")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataConsulta;
}