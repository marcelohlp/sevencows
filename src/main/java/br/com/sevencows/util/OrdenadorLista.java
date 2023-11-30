package br.com.sevencows.util;

import java.util.List;

import br.com.sevencows.interfaces.PossuiData;
import br.com.sevencows.model.RegistroFinanceiro;

public abstract class OrdenadorLista {

	public static void ordenarData(List<? extends PossuiData> lista) {

		try {

			lista.sort((i1, i2) -> i1.getData().compareTo(i2.getData()));

		} catch (ClassCastException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (NullPointerException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

	}

	public static void ordenarValor(List<? extends RegistroFinanceiro> lista) {

		try {

			lista.sort((i1, i2) -> Double.compare(i1.getValor(), i2.getValor()));

		} catch (ClassCastException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (NullPointerException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

	}

}
