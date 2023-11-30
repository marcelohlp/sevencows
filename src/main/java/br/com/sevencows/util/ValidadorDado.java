package br.com.sevencows.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;

import br.com.sevencows.exception.ValorException;

public abstract class ValidadorDado {

	public static String validarDataInicial(String dataInicial) {

		String dt = null;

		try {

			if (dataInicial != null && dataInicial != "") {

				if (validarFormato(dataInicial)) {
					
					dt = ConversorData.stringFrontBack(dataInicial);
					
				}

			} else {

				dt = ConversorData.localDateString(LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()));

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			
		}

		return dt;

	}
	
	public static String validarDataFinal(String dataFinal) {

		String dt = null;

		try {

			if (dataFinal != null && dataFinal != "") {

				if (validarFormato(dataFinal)) {
					
					dt = ConversorData.stringFrontBack(dataFinal);
					
				}

			} else {

				dt = ConversorData.localDateString(LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());
			
		}

		return dt;

	}

	private static boolean validarFormato(String data) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			
			LocalDate.parse(data, formatter);
			
			return true;
		
		} catch(DateTimeParseException e) {
			
			e.printStackTrace();
			System.err.println(e.getMessage());
			
			return false;
			
		} catch(NullPointerException e) {
			
			e.printStackTrace();
			System.err.println(e.getMessage());
			
			return false;
			
		} catch(Exception e) {
			
			e.printStackTrace();
			System.err.println(e.getMessage());
			
			return false;
			
		}
		
	}
	
	public static void validaValor(double valor) throws ValorException {
		
		if (valor < 0) {
			
			throw new ValorException();
	
		}
		
	}
}
