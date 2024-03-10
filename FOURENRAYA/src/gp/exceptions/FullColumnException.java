package gp.exceptions;

public class FullColumnException extends Exception{
	public FullColumnException() { super(); }
	public FullColumnException(String message){ super(message); }
	public FullColumnException(String message, Throwable cause){
		super(message, cause);
	}
	public FullColumnException(Throwable cause){ super(cause); }
	
	public FullColumnException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}