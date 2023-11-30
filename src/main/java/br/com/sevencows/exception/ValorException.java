package br.com.sevencows.exception;

public class ValorException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValorException() {
		super();
	}

	public ValorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ValorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValorException(String message) {
		super(message);
	}

	public ValorException(Throwable cause) {
		super(cause);
	}

}
