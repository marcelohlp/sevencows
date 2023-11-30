package br.com.sevencows.util;

public abstract class ConversorNumerico {

	public static double stringDouble(String numero) {

		double db = -1;

		try {

			String s = numero.replace(",", ".");

			db = Double.parseDouble(s);

		} catch (NumberFormatException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (NullPointerException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return db;

	}

	public static int stringInt(String numero) {

		int i = -1;
		
		try {
			
			i = Integer.parseInt(numero);
			
		} catch (NumberFormatException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (NullPointerException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}
		
		return i;
		
	}
	
}
