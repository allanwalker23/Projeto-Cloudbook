package com.cloudbook.projeto.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.cloudbook.projeto.domain.Conteudo;
import com.cloudbook.projeto.domain.enums.TipoConteudo;

public class ConteudoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String assunto;
	private Date data_criacao;
	private Integer tipo;
	
	public ConteudoDTO() {
	
	}
	
	public ConteudoDTO(Conteudo conteudo) {
		assunto = conteudo.getAssunto();
		data_criacao= conteudo.getData_criacao();
		tipo = (conteudo.getTipo()==null)?null:conteudo.getTipo().getCod();
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public TipoConteudo getTipo() {
		return TipoConteudo.toEnum(tipo);
	}

	public void setTipo(TipoConteudo tipo) {
		this.tipo = tipo.getCod();
	}
	
	
}