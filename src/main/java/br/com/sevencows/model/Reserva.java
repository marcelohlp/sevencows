package br.com.sevencows.model;

public class Reserva extends RegistroFinanceiro {

	private String objetivo;
	private double valorObjetivo;

	public Reserva() {

	}

	public Reserva(int codigoUsuario, String descricao, double valor, String objetivo, double valorObjetivo) {
		super(codigoUsuario, descricao, valor);
		this.objetivo = objetivo;
		this.valorObjetivo = valorObjetivo;
	}

	public Reserva(int codigo, int codigoUsuario, String descricao, double valor, String objetivo,
			double valorObjetivo) {
		super(codigo, codigoUsuario, descricao, valor);
		this.objetivo = objetivo;
		this.valorObjetivo = valorObjetivo;
	}

	@Override
	public String toString() {
		return "Descrição: " + descricao + "\n" + "Objetivo: " + objetivo + "\n" + "Valor atual: R$ " + valor + "\n"
				+ "Valor meta: R$ " + valorObjetivo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public double getValorObjetivo() {
		return valorObjetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public void setValorObjetivo(double valorObjetivo) {
		this.valorObjetivo = valorObjetivo;
	}

}
