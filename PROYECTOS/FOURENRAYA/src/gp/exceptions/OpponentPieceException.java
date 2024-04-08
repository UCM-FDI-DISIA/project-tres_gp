package gp.exceptions;

public class OpponentPieceException extends Exception{
	public OpponentPieceException() { super(); }
	public OpponentPieceException(String message){ super(message); }
	public OpponentPieceException(String message, Throwable cause){
		super(message, cause);
	}
	public OpponentPieceException(Throwable cause){ super(cause); }
	
	public OpponentPieceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}
}