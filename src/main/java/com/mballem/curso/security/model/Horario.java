package com.mballem.curso.security.model;

import java.time.LocalTime;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "horas", indexes = {
		@Index(name = "idx_hora_minuto", columnList = "hora_minuto") // índice para busca rápida
})
@Data
@NoArgsConstructor // construtor vazio
@EqualsAndHashCode(callSuper = true) // inclui id da superclasse no equals e hashCode
public class Horario extends AbstractEntity {

	// Campo único e obrigatório que guarda hora e minuto
	@Column(name = "hora_minuto", unique = true, nullable = false)
	private LocalTime horaMinuto;
}
