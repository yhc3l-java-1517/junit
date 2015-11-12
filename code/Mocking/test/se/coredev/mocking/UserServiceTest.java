package se.coredev.mocking;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import se.coredev.mocking.model.User;
import se.coredev.mocking.repository.RepositoryException;
import se.coredev.mocking.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public final class UserServiceTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Mock private UserRepository userRepositoryMock;
	@Mock private IdGenerator idGeneratorMock;
	@InjectMocks private UserService service;
	
	private final String userId = "1001";
	private final String username = "Yoda";
	private final String password = "secret";

	@Test
	public void shouldThrowServiceExceptionWhenUsernameIsAlreadyTaken() {

		// Setup expectation
		thrown.expect(ServiceException.class);
		thrown.expectMessage("Username is already taken");

		// Setup mock object
		when(userRepositoryMock.usernameExists(username)).thenReturn(true);

		// Test method
		service.createUser(username, "password");

		// Verify method invocation on mock object
		verify(userRepositoryMock).usernameExists(username);
	}

	@Test
	public void canCreateUser() {

		// Setup mock
		when(userRepositoryMock.usernameExists(username)).thenReturn(false);
		when(idGeneratorMock.generate()).thenReturn(userId);

		// Test method
		User user = service.createUser(username, password);

		// Assert result
		assertThat(user.getId(), equalTo(userId));
		assertThat(user.getUsername(), equalTo(username));
		assertThat(user.getPassword(), equalTo(password));

		// Verify method invocation on mock objects
		verify(idGeneratorMock).generate();
		verify(userRepositoryMock).add(user);
	}

	@Test
	public void willThrowServiceExceptionForNonExistingUser() throws RepositoryException {
		// Setup mock
		when(userRepositoryMock.get(userId)).thenThrow(new RepositoryException(""));

		// Setup expectation
		thrown.expect(ServiceException.class);

		// Test method
		service.getUserById(userId);

		// Verify method invocation on mock objects
		verify(userRepositoryMock).get(userId);
	}

}
