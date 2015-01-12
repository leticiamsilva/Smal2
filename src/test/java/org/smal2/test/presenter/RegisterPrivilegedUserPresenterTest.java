package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.RegisterPrivilegedUserPresenter;
import org.smal2.presentation.view.IRegisterPrivilegedUserView;
import org.smal2.presentation.view.basic.RegisterPrivilegedUserViewMock;
import org.smal2.service.user.RegisterPrivilegedUserRequest;
import org.smal2.service.user.UserType;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterPrivilegedUserPresenterTest extends ABaseTest {

	@Before
	public void before() {
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
		IRegisterPrivilegedUserView view = new RegisterPrivilegedUserViewMock();

		// Act
		new RegisterPrivilegedUserPresenter(view, userService);
		view.setRequest(new RegisterPrivilegedUserRequest("0004", "password",
				"Jessy", "admin@smal.org", UserType.ADMINISTRATOR));
		view.getCommand().execute();

		// Assert
		String md5Pass = MD5Generator.generate("password");
		Assert.assertEquals(4, userRepository.listAll().size());
		Assert.assertEquals("0004", userRepository.getByRegistration("0004")
				.getRegistration());
		Assert.assertEquals(md5Pass, userRepository.getByRegistration("0004")
				.getPassword());
		Assert.assertEquals("Jessy", userRepository.getByRegistration("0004")
				.getName());
		Assert.assertEquals("admin@smal.org",
				userRepository.getByRegistration("0004").getEmail());
		Assert.assertEquals("User registred successfully.", view.getResponse());
	}

	@Test
	public void registerStudentMustShowError() {
		// Arrange
		IRegisterPrivilegedUserView view = new RegisterPrivilegedUserViewMock();

		// Act
		new RegisterPrivilegedUserPresenter(view, userService);
		view.setRequest(new RegisterPrivilegedUserRequest("0005", "password",
				"Jimmy", "user@smal.org", UserType.STUDENT));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(
				"Register user error:\nUser type must be administrator or technician.",
				view.getError());
	}
}
