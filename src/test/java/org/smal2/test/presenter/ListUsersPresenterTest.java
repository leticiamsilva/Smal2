package org.smal2.test.presenter;

import org.smal2.presentation.presenter.ListUsersPresenter;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.test.presenter.mock.ListUsersViewMock;
import org.junit.Assert;
import org.junit.Test;

public class ListUsersPresenterTest extends AUsersPresenterTest {

	@Test
	public void listMustReturnAllUsers() {
		// Arrange
		IListUsersView view = new ListUsersViewMock();

		// Act
		new ListUsersPresenter(view, userService);

		// Assert
		Assert.assertEquals(userRepository.listAll().size(), view.getResponse()
				.size());
	}
}
