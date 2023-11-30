package br.com.sevencows.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;

public abstract class ConversorData {

	public static Date localDateDate(LocalDate data) {

		Date dt = null;

		try {

			dt = Date.valueOf(data);

		} catch (NullPointerException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (DateTimeException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

	public static LocalDate dateLocalDate(Date data) {

		LocalDate dt = null;

		try {

			dt = data.toLocalDate();

		} catch (NullPointerException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (DateTimeException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

	public static LocalDate stringLocalDate(String dataCompleta) {

		LocalDate ld = null;

		try {

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			ld = LocalDate.parse(dataCompleta, fmt);

		} catch (DateTimeParseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return ld;
	}

	public static Date stringDate(String dataCompleta) {

		Date dt = null;

		try {

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			LocalDate ld = LocalDate.parse(dataCompleta, fmt);

			dt = Date.valueOf(ld);

		} catch (DateTimeParseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (DateTimeException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;
	}

	public static String localDateString(LocalDate data) {

		String dt = null;

		try {

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			dt = data.format(fmt);

		} catch (DateTimeParseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (UnsupportedTemporalTypeException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

	public static String localDateFront(LocalDate data) {

		String dt = null;

		try {

			DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			dt = data.format(fmt);

		} catch (DateTimeParseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (UnsupportedTemporalTypeException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

	public static String dataString(Date data) {

		String dt = null;

		try {

			LocalDate ld = dateLocalDate(data);

			dt = localDateString(ld);

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

	public static String stringFrontBack(String dataFront) {

		String dt = null;

		try {

			SimpleDateFormat formatoFront = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date data = formatoFront.parse(dataFront);

			// Formata a data como "dd/MM/yyyy"
			DateFormat paraString = new SimpleDateFormat("dd/MM/yyyy");
			dt = paraString.format(data);

		} catch (ParseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

	public static String stringBackFront(String dataBack) {

		String dt = null;

		try {

			SimpleDateFormat formatoBack = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date data = formatoBack.parse(dataBack);

			// Formata a data como "dd/MM/yyyy"
			DateFormat paraString = new SimpleDateFormat("yyyy-MM-dd");
			dt = paraString.format(data);

		} catch (ParseException e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();
			System.err.println(e.getMessage());

		}

		return dt;

	}

}
