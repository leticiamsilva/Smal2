package org.smal2.test.service;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.User;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.test.testutil.ABaseTest;

public class AuthenticateUserServiceTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("test_login", "test_login", new Date()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void authenticateUserOnNullUsernameMustThrowException() {
		// Act
		authService.loginUser(new LoginUserRequest(null, "1234"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void authenticateUserOnNullPasswordMustThrowException() {
		// Act
		authService.loginUser(new LoginUserRequest("1234", null));
	}

	@Test(expected = IllegalArgumentException.class)
	public void authenticateUserOnEmptyUsernameMustThrowException() {
		// Act
		authService.loginUser(new LoginUserRequest("", "1234"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void authenticateUserOnEmptyPasswordMustThrowException() {
		// Act
		authService.loginUser(new LoginUserRequest("1234", ""));
	}

	@Test(expected = IllegalArgumentException.class)
	public void authenticateUserWithInvalidUsernameMustThrowException() {
		// Act
		authService.loginUser(new LoginUserRequest("1234", "test_login"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void authenticateUserWithInvalidPasswordMustThrowException() {
		// Act
		authService.loginUser(new LoginUserRequest("test_login", "1234"));
	}

	public void authenticateExistingUserWithValidAuthMustLogin() {
		// Act
		authService.loginUser(new LoginUserRequest("test_login", "test_login"));
	}

	public void authenticateUnexistingUserWithValidAuthMustRegisterUserAndLogin() {
		// Act
		authService
				.loginUser(new LoginUserRequest("test_login2", "test_login2"));
	}
}
