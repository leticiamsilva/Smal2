package org.smal2.test.presenter;

import java.util.Date;

import org.smal2.domain.User;
import org.smal2.domain.repository.UserRepository;
import org.smal2.presenter.ListUsersPresenter;
import org.smal2.presenter.view.IListUsersView;
import org.smal2.service.user.UserService;
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
		userRepository.insert(new User("0001", "Jhon", new Date()));
		userRepository.insert(new User("0002", "Jack", new Date()));
		userRepository.insert(new User("0003", "Joe", new Date()));

		// Act
		IListUsersView view = new ListUsersViewMock();
		new ListUsersPresenter(view, userService);

		// Assert
		Assert.assertEquals(userRepository.listAll().size(), view.getResponse()
				.size());
	}
}
