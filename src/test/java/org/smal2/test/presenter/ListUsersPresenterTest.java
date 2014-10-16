package org.smal2.test.presenter;

import java.util.Date;

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
		userRepository.insert(new User("0001", "Jhon", new Date()));
		userRepository.insert(new User("0002", "Jack", new Date()));
		userRepository.insert(new User("0003", "Joe", new Date()));
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
