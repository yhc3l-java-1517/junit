package se.coredev.mocking.repository;

public class RepositoryException extends Exception {

	private static final long serialVersionUID = 20151111L;

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}
}
