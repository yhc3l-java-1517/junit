package se.coredev.mocking.repository;

import se.coredev.mocking.model.User;

public interface UserRepository {

	boolean usernameExists(String username);

	void add(User user);

	User get(String userId) throws RepositoryException;
	
	boolean authenticate(String username, String password) throws RepositoryException;

}
