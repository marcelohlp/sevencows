package br.com.sevencows.model;

import java.util.Objects;

public class Frequencia {

	private int codigo;
	private String descricao;

	public Frequencia() {

	}

	public Frequencia(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(descricao);
	}

	@Override
	public boolean equals(Object objeto) {
		
		if (this == objeto) {
			return true;
		} else if (objeto == null || this.getClass() != objeto.getClass()) {
			return false;
		} else {
			Frequencia frequencia = (Frequencia) objeto;
			return Objects.equals(codigo, frequencia.getCodigo());
		}
		
	}
	
	public String toString() {
		return "Código: " + codigo + "\n" 
				+ "Descrição: " + descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
