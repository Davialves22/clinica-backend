package com.mballem.curso.security.config.datatables;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Datatables {

	private HttpServletRequest request; // Recebe a requisição HTTP do datatables
	private String[] colunas; // Nomes das colunas da tabela

	public Datatables() {
		super();
	}

	// Gera o JSON de resposta para o datatables com paginação
	public Map<String, Object> getResponse(Page<?> page) {
		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw()); // Número da requisição para controle
		json.put("recordsTotal", page.getTotalElements()); // Total de registros no banco
		json.put("recordsFiltered", page.getTotalElements()); // Total após filtro
		json.put("data", page.getContent()); // Dados da página atual
		return json;
	}

	// Getters e setters da request e colunas
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String[] getColunas() {
		return colunas;
	}

	public void setColunas(String[] colunas) {
		this.colunas = colunas;
	}

	// Recupera o parâmetro 'draw' da requisição (número da requisição)
	private int draw() {
		return Integer.parseInt(this.request.getParameter("draw"));
	}

	// Recupera o parâmetro 'start' (posição inicial dos registros da página)
	private int start() {
		return Integer.parseInt(this.request.getParameter("start"));
	}

	// Recupera o parâmetro 'length' (quantidade de registros por página)
	public int getLength() {
		return Integer.parseInt(this.request.getParameter("length"));
	}

	// Calcula a página atual (dividindo start por length)
	public int getCurrentPage() {
		return start() / getLength();
	}

	// Recupera o nome da coluna para ordenar, usando o índice do parâmetro
	public String getColumnName() {
		int iCol = Integer.parseInt(this.request.getParameter("order[0][column]"));
		return this.colunas[iCol];
	}

	// Recupera a direção da ordenação (ASC ou DESC)
	public Sort.Direction getDirection() {
		String order = this.request.getParameter("order[0][dir]");
		Sort.Direction sort = Sort.Direction.ASC;
		if (order.equalsIgnoreCase("desc")) {
			sort = Sort.Direction.DESC;
		}
		return sort;
	}

	// Recupera o texto para busca/filtro
	public String getSearch() {
		return this.request.getParameter("search[value]");
	}

	// Cria o objeto Pageable para o Spring Data, com página, tamanho, direção e
	// coluna
	public Pageable getPageable() {
		return PageRequest.of(getCurrentPage(), getLength(), getDirection(), getColumnName());
	}
}