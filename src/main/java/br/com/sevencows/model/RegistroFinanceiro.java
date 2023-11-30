package br.com.sevencows.model;

import java.util.Objects;

public abstract class RegistroFinanceiro {

	protected int codigo;
	protected int codigoUsuario;
	protected String descricao;
	protected double valor;

	public RegistroFinanceiro() {

	}

	public RegistroFinanceiro(int codigoUsuario, String descricao, double valor) {
		this.codigoUsuario = codigoUsuario;
		this.descricao = descricao;
		this.valor = valor;
	}

	public RegistroFinanceiro(int codigo, int codigoUsuario, String descricao, double valor) {
		this.codigo = codigo;
		this.codigoUsuario = codigoUsuario;
		this.descricao = descricao;
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricao, valor);
	}

	@Override
	public boolean equals(Object objeto) {
		
		if (this == objeto) {
			return true;
		} else if (objeto == null || this.getClass() != objeto.getClass()) {
			return false;
		} else {
			RegistroFinanceiro registro = (RegistroFinanceiro) objeto;
			return Objects.equals(codigo, registro.getCodigo());
		}
		
	}
	
	public abstract String toString();

	public int getCodigo() {
		return codigo;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
