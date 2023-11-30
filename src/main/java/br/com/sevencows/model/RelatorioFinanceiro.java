package br.com.sevencows.model;

import java.util.List;

public class RelatorioFinanceiro {

	private double valorTotalReceitas;
	private double valorTotalDespesas;
	private double valorResultado;
	private double valorAtualReservas;
	private double valorObjetivoReservas;
	private double valorDiferencaReserva;

	public RelatorioFinanceiro() {

	}

	public RelatorioFinanceiro(List<Receita> listaReceitas, List<Despesa> listaDespesas, List<Reserva> listaReservas) {
		valorTotalReceitas = somarValoresLista(listaReceitas);
		valorTotalDespesas = somarValoresLista(listaDespesas);
		valorAtualReservas = somarValoresLista(listaReservas);
		valorObjetivoReservas = somarValoresObjetivo(listaReservas);
		valorResultado = calcularDiferenca(valorTotalReceitas, valorTotalDespesas);
		valorDiferencaReserva = calcularDiferenca(valorObjetivoReservas, valorAtualReservas);
	}

	private double somarValoresLista(List<? extends RegistroFinanceiro> lista) {
		double total = 0;
		if (lista != null && !lista.isEmpty()) {
			for (RegistroFinanceiro r : lista) {
				total += r.getValor();
			}
		}
		return total;
	}

	private double somarValoresObjetivo(List<Reserva> lista) {
		double total = 0;
		if (lista != null && !lista.isEmpty()) {
			for (Reserva r : lista) {
				total += r.getValorObjetivo();
			}
		}
		return total;
	}

	private double calcularDiferenca(double valorUm, double valorDois) {
		return valorUm - valorDois;
	}

	public double getValorTotalReceitas() {
		return valorTotalReceitas;
	}

	public double getValorTotalDespesas() {
		return valorTotalDespesas;
	}

	public double getValorResultado() {
		return valorResultado;
	}

	public double getValorAtualReservas() {
		return valorAtualReservas;
	}

	public double getValorObjetivoReservas() {
		return valorObjetivoReservas;
	}

	public double getValorDiferencaReserva() {
		return valorDiferencaReserva;
	}

	public void setValorTotalReceitas(double valorTotalReceitas) {
		this.valorTotalReceitas = valorTotalReceitas;
	}

	public void setValorTotalDespesas(double valorTotalDespesas) {
		this.valorTotalDespesas = valorTotalDespesas;
	}

	public void setValorResultado(double valorResultado) {
		this.valorResultado = valorResultado;
	}

	public void setValorAtualReservas(double valorAtualReservas) {
		this.valorAtualReservas = valorAtualReservas;
	}

	public void setValorObjetivoReservas(double valorObjetivoReservas) {
		this.valorObjetivoReservas = valorObjetivoReservas;
	}

	public void setValorDiferencaReserva(double valorDiferencaReserva) {
		this.valorDiferencaReserva = valorDiferencaReserva;
	}

}
