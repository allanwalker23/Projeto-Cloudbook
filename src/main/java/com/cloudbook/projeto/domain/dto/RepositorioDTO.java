package com.cloudbook.projeto.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.cloudbook.projeto.domain.Repositorio;

public class RepositorioDTO implements Serializable{
private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Date data;
	private String descricao;
	
	public RepositorioDTO() {
	
	}
	
	public RepositorioDTO(Repositorio obj) {
		id = obj.getId();
		nome = obj.getNome();
		data = obj.getData();
		descricao = obj.getDescricao();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
