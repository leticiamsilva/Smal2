package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.AuthUserPresenter;
import org.smal2.presentation.view.IAuthUserView;
import org.smal2.presentation.view.basic.AuthUserViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticateUserPresenterTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("registration", MD5Generator
				.generate("password"), "name"));
	}

	@Test
	public void authenticateUserWithValidAuthMustReturnLoginSessionID() {
		// Arrange
		IAuthUserView view = new AuthUserViewMock();

		// Act
		new AuthUserPresenter(view, authService);
		view.setRequest(new LoginUserRequest("registration", "password"));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals("User authenticated successfully.", view
				.getResponse().getMessage());
		Assert.assertEquals(userRepository.listAll().get(0).getSession(), view
				.getResponse().getSession());
	}

	@Test
	public void authenticateUserWithInvalidAuthMustShowErrorMessage() {
		// Arrange
		IAuthUserView view = new AuthUserViewMock();

		// Act
		new AuthUserPresenter(view, authService);
		view.setRequest(new LoginUserRequest("registration", "password2"));
		view.getCommand().execute();

		// Assert
		Assert.assertTrue(view.getError().contains("Login user error"));
	}
}