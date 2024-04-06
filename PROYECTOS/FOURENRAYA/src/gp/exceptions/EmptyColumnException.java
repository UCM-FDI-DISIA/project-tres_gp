package gp.exceptions;

public class EmptyColumnException extends Exception{
	public EmptyColumnException() { super(); }
	public EmptyColumnException(String message){ super(message); }
	public EmptyColumnException(String message, Throwable cause){
		super(message, cause);
	}
	public EmptyColumnException(Throwable cause){ super(cause); }
	
	public EmptyColumnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
