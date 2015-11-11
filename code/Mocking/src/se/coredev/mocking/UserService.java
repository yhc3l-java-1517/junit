package se.coredev.mocking;

import se.coredev.mocking.model.User;
import se.coredev.mocking.repository.RepositoryException;
import se.coredev.mocking.repository.UserRepository;

public final class UserService {

	private final UserRepository userRepository;
	private final IdGenerator idGenerator;

	public UserService(UserRepository userRepository, IdGenerator idGenerator) {
		this.userRepository = userRepository;
		this.idGenerator = idGenerator;
	}

	public User createUser(String username, String password) {

		if (userRepository.usernameExists(username)) {
			throw new ServiceException("Username is already taken");
		}

		String id = idGenerator.generate();
		User user = new User(id, username, password);

		userRepository.add(user);

		return user;
	}

	public User getUserById(String userId) {
		try {
			return userRepository.get(userId);
		}
		catch (RepositoryException e) {
			throw new ServiceException("User with id: " + userId + " could not be found", e);
		}
	}

}
