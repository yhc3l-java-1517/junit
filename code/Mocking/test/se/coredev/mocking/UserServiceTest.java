package se.coredev.mocking;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import se.coredev.mocking.model.User;
import se.coredev.mocking.repository.RepositoryException;
import se.coredev.mocking.repository.UserRepository;

public class UserServiceTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private UserRepository userRepositoryMock;
	private IdGenerator idGeneratorMock;
	private UserService service;
	private String userId = "1001";

	@Before
	public void setup() {
		userRepositoryMock = mock(UserRepository.class);
		idGeneratorMock = mock(IdGenerator.class);
		service = new UserService(userRepositoryMock, idGeneratorMock);
	}

	@Test
	public void shouldThrowServiceExceptionWhenUsernameIsAlreadyTaken() {

		String username = "Yoda";

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

		String username = "Yoda";
		String password = "secret";

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
	public void willThrowServiceExceptionForNonExistingUser() throws RepositoryException
	{
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
