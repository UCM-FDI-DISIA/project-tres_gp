package gp.exceptions;

public class CantChangeModeException extends Exception{
	public CantChangeModeException() { super(); }
	public CantChangeModeException(String message){ super(message); }
	public CantChangeModeException(String message, Throwable cause){
		super(message, cause);
	}
	public CantChangeModeException(Throwable cause){ super(cause); }
	
	public CantChangeModeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}