package se.coredev.zoo;

public class ZooException extends RuntimeException {

	private static final long serialVersionUID = 5040288574346096541L;

	public ZooException(String message, Throwable cause) {
		super(message, cause);
	}

	public ZooException(String message) {
		super(message);
	}

	public ZooException(Throwable cause) {
		super(cause);
	}

}
