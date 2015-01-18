package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.presentation.presenter.ListUsersPresenter;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.presentation.view.basic.ListUsersViewMock;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.user.ListUsersRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListUsersPresenterTest extends ABaseTest {

	private String technician_session_id;

	@Before
	public void before() {
		userRepository.insert(new User("0000", MD5Generator.generate("pass"),
				"Jhon", org.smal2.domain.entity.UserType.TECHNICIAN));

		technician_session_id = authService.loginUser(
				new LoginUserRequest("0000", "pass")).getSession_id();

		userRepository.insert(new User("0001", MD5Generator.generate("pass"),
				"Jhon", UserType.STUDENT));
		userRepository.insert(new User("0002", MD5Generator.generate("pass"),
				"Jack", UserType.STUDENT));
		userRepository.insert(new User("0003", MD5Generator.generate("pass"),
				"Joe", UserType.STUDENT));
	}

	@Test
	public void listMustReturnAllUsers() {
		// Arrange
		IListUsersView view = new ListUsersViewMock();

		// Act
		view.setRequest(new ListUsersRequest(technician_session_id));
		new ListUsersPresenter(view, userService);
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(4, view.getResponse().size());
	}
}
