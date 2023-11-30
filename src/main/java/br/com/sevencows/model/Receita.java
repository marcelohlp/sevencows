package br.com.sevencows.model;

import java.time.LocalDate;

import br.com.sevencows.interfaces.PossuiData;
import br.com.sevencows.util.ConversorData;

public class Receita extends RegistroFinanceiro implements PossuiData{

	private Frequencia frequencia;
	private LocalDate data;

	public Receita() {

	}

	public Receita(int codigoUsuario, String descricao, double valor, Frequencia frequencia, LocalDate data) {
		super(codigoUsuario, descricao, valor);
		this.frequencia = frequencia;
		this.data = data;
	}

	public Receita(int codigo, int codigoUsuario, String descricao, double valor, Frequencia frequencia,
			LocalDate data) {
		super(codigo, codigoUsuario, descricao, valor);
		this.frequencia = frequencia;
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "Descrição: " + descricao + "\n"
				+ "Valor: R$ " + valor + "\n"
				+ "Data: " + ConversorData.localDateString(data) + "\n"
				+ "Frequencia: " + frequencia.getDescricao();
	}

	public Frequencia getFrequencia() {
		return frequencia;
	}

	@Override
	public LocalDate getData() {
		return data;
	}

	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
