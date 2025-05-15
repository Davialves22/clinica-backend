package com.mballem.curso.security.model;

public enum PerfilTipo {
	ADMIN(1, "ADMIN"), MEDICO(2, "MEDICO"), PACIENTE(3, "PACIENTE");// numero do id no bd

	private long cod;
	private String desc;

	private PerfilTipo(long cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}

	public long getCod() {
		return cod;
	}

	public String getDesc() {
		return desc;
	}
}