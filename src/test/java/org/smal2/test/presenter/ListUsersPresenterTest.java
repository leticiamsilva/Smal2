package org.smal2.test.presenter;

import org.smal2.domain.User;
import org.smal2.domain.repository.UserRepository;
import org.smal2.presenter.UserListPresenter;
import org.smal2.presenter.view.I_UserListView;
import org.smal2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class ListUsersPresenterTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Before
	public void before() {
		/*
		 * calc = null; frete = Mockito.mock(IFrete.class);
		 * Mockito.when(frete.getValor(compra)).thenReturn(0.0); desconto =
		 * Mockito.mock(IDesconto.class);
		 * Mockito.when(desconto.getValor(compra)).thenReturn(0.0); valor = 0;
		 */
	}

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(userRepository);
	}

	@Test
	public void listMustReturnAllUsers() {
		// Arrange
		userRepository.insert(new User("Jhon", "0001"));
		userRepository.insert(new User("Jack", "0002"));
		userRepository.insert(new User("Joe", "0003"));

		// Act
		I_UserListView view = new UserListViewMock();
		new UserListPresenter(view, userService);

		// Assert
		Assert.assertEquals(userRepository.listAll().size(), view.getUsers()
				.size());
		// CollectionAssert.AreEquivalent(logs.Select(l => l.Name).ToList(),
		// view.Logs.ToList());
	}
}
