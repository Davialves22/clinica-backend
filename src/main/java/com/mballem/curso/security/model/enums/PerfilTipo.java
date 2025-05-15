package com.mballem.curso.security.model.enums;

// Enum que define os tipos de perfil do sistema
public enum PerfilTipo {
	ADMIN(1, "ADMIN"), // Perfil administrador com código 1
	MEDICO(2, "MEDICO"), // Perfil médico com código 2
	PACIENTE(3, "PACIENTE"); // Perfil paciente com código 3

	private long cod; // Código numérico do perfil (id no banco)
	private String desc; // Descrição do perfil

	// Construtor privado da enum
	private PerfilTipo(long cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	// Retorna o código do perfil
	public long getCod() {
		return cod;
	}

	// Retorna a descrição do perfil
	public String getDesc() {
		return desc;
	}
}
