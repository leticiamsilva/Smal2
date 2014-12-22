package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.ListUsersPresenter;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.presentation.view.basic.ListUsersViewMock;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListUsersPresenterTest extends ABaseTest {

	@Before
	public void before() {
		userRepository.insert(new User("0001", MD5Generator.generate("pass"),
				"Jhon"));
		userRepository.insert(new User("0002", MD5Generator.generate("pass"),
				"Jack"));
		userRepository.insert(new User("0003", MD5Generator.generate("pass"),
				"Joe"));
	}

	@Test
	public void listMustReturnAllUsers() {
		// Arrange
		IListUsersView view = new ListUsersViewMock();

		// Act
		new ListUsersPresenter(view, userService);
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(3, view.getResponse().size());
	}
}
