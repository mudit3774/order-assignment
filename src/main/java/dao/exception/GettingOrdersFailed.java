package dao.exception;

public class GettingOrdersFailed extends RuntimeException {
	public GettingOrdersFailed(String message, Throwable cause) {
		super(message, cause);
	}
}
