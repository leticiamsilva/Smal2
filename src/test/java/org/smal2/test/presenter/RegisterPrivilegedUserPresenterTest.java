package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.RegisterPrivilegedUserPresenter;
import org.smal2.presentation.view.IRegisterPrivilegedUserView;
import org.smal2.presentation.view.basic.RegisterPrivilegedUserViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.user.RegisterPrivilegedUserRequest;
import org.smal2.service.user.UserType;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterPrivilegedUserPresenterTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.ADMINISTRATOR));
		userRepository.insert(new User("0001", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.STUDENT));
		userRepository.insert(new User("0002", MD5Generator.generate("pass"),
				"Jack", org.smal2.domain.entity.UserType.STUDENT));
		userRepository.insert(new User("0003", MD5Generator.generate("pass"),
				"Joe", org.smal2.domain.entity.UserType.STUDENT));
	}

	@Test
	public void registerAdminMustSaveAdmin() {
		// Arrange
		String session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		IRegisterPrivilegedUserView view = new RegisterPrivilegedUserViewMock();

		// Act
		new RegisterPrivilegedUserPresenter(view, userService);
		view.setRequest(new RegisterPrivilegedUserRequest(session_id, "0004",
				"password", "Jessy", "admin@smal.org", UserType.ADMINISTRATOR));
		view.getCommand().execute();

		// Assert
		String md5Pass = MD5Generator.generate("password");
		Assert.assertEquals(5, userRepository.listAll().size());
		Assert.assertEquals("0004", userRepository.get("0004")
				.getRegistration());
		Assert.assertEquals(md5Pass, userRepository.get("0004").getPassword());
		Assert.assertEquals("Jessy", userRepository.get("0004").getName());
		Assert.assertEquals("admin@smal.org", userRepository.get("0004")
				.getEmail());
		Assert.assertEquals("User registred successfully.", view.getResponse());
	}

	@Test
	public void registerStudentMustShowError() {
		// Arrange
		String session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		IRegisterPrivilegedUserView view = new RegisterPrivilegedUserViewMock();

		// Act
		new RegisterPrivilegedUserPresenter(view, userService);
		view.setRequest(new RegisterPrivilegedUserRequest(session_id, "0005",
				"password", "Jimmy", "user@smal.org", UserType.STUDENT));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(
				"Register user error:\nUser type must be administrator or technician.",
				view.getError());
	}
}
