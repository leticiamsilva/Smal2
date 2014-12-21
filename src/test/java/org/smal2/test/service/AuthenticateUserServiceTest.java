package org.smal2.test.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.User;
import org.smal2.persistence.IAuthDAO;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.test.testutil.ABaseTest;
import org.smal2.test.testutil.mock.AuthDAOMock;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenticateUserServiceTest extends ABaseTest {

	@Autowired
	private IAuthDAO authDao;

	@Before
	public void before() {
		userRepository.insert(new User("local_test_login", "local_test_login",
				new Date()));
		((AuthDAOMock) authDao).create("test_login", "test_login");
	}

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(authDao);
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
		authService.loginUser(new LoginUserRequest("local_test_login",
				"local_test_login"));
	}

	public void authenticateUnexistingUserWithValidAuthMustRegisterUserAndLogin() {
		// Act
		authService.loginUser(new LoginUserRequest("test_login", "test_login"));
	}
}
