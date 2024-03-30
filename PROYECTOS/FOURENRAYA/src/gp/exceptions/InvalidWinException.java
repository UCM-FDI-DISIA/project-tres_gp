package gp.exceptions;

public class InvalidWinException extends Exception{
	public InvalidWinException() { super(); }
	public InvalidWinException(String message){ super(message); }
	public InvalidWinException(String message, Throwable cause){
		super(message, cause);
	}
	public InvalidWinException(Throwable cause){ super(cause); }
	
	public InvalidWinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
