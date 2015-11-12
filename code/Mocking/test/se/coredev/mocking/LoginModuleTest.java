package se.coredev.mocking;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import se.coredev.mocking.repository.RepositoryException;
import se.coredev.mocking.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginModuleTest {

	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private LoginModule loginModule;

	@Test
	public void willRetryOnFailure() throws RepositoryException {

		String username = "yoda";
		String password = "secret";

		when(userRepositoryMock.authenticate(username, password)).thenThrow(new RepositoryException("Network is down"));

		loginModule.login(username, password);

		verify(userRepositoryMock, times(3)).authenticate(username, password);
	}

	@Test
	public void testSpy(){
		
		List<String> list = new ArrayList<>();
		List<String> spy = Mockito.spy(list);
		
		when(spy.size()).thenReturn(100);
		
		spy.add("one");
		spy.add("two");
		
		System.out.println(spy.get(0));
		System.out.println(spy.get(1));
		System.out.println(spy.size());
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
