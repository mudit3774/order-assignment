package dao.exception;

public class OrderInsertFailedException extends RuntimeException {
	public OrderInsertFailedException(String message, Throwable cause) {
		super(message, cause);
	}
}
