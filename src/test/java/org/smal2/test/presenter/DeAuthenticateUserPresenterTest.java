package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.DeAuthUserPresenter;
import org.smal2.presentation.view.IDeAuthUserView;
import org.smal2.presentation.view.basic.DeAuthUserViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.auth.LoginUserResponse;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeAuthenticateUserPresenterTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("registration", MD5Generator
				.generate("password"), "name"));
	}

	@Test
	public void deauthenticateUserWithValidSessionIdMustReturnConfirmMessage() {
		// Arrange
		LoginUserResponse loginResponse = authService
				.loginUser(new LoginUserRequest("registration", "password"));
		IDeAuthUserView view = new DeAuthUserViewMock();

		// Act
		new DeAuthUserPresenter(view, authService);
		view.setRequest(loginResponse.getSession_id());
		view.getCommand().execute();

		// Assert
		Assert.assertEquals("User deauthenticated successfully.",
				view.getResponse());
		User user = userRepository.getByRegistration("registration");
		Assert.assertEquals(user.getSession_id(), null);
	}

	@Test
	public void deauthenticateUserWithInvalidSessionIdMustShowErrorMessage() {
		// Arrange
		IDeAuthUserView view = new DeAuthUserViewMock();

		// Act
		new DeAuthUserPresenter(view, authService);
		view.setRequest("1234");
		view.getCommand().execute();

		// Assert
		Assert.assertTrue(view.getError().contains("Logout user error"));
	}
}
