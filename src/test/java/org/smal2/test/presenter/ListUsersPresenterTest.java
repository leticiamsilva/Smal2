package org.smal2.test.presenter;

import org.smal2.domain.User;
import org.smal2.domain.repository.UserRepository;
import org.smal2.presenter.ListUsersPresenter;
import org.smal2.presenter.view.I_ListUsersView;
import org.smal2.service.UserService;
import org.smal2.test.presenter.mock.ListUsersViewMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class ListUsersPresenterTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(userRepository);
		Assert.assertNotNull(userService);
	}

	@Test
	public void listMustReturnAllUsers() {
		// Arrange
		userRepository.insert(new User("Jhon", "0001"));
		userRepository.insert(new User("Jack", "0002"));
		userRepository.insert(new User("Joe", "0003"));

		// Act
		I_ListUsersView view = new ListUsersViewMock();
		new ListUsersPresenter(view, userService);

		// Assert
		Assert.assertEquals(userRepository.listAll().size(), view.getUsers()
				.size());
	}
}
