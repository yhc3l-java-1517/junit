package se.coredev.mocking;

public final class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 20151111L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	
}
