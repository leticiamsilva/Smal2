package org.smal2.test.presenter;

import java.util.Date;
import java.util.GregorianCalendar;

import org.smal2.domain.User;
import org.smal2.domain.repository.UserRepository;
import org.smal2.presenter.RegisterUserPresenter;
import org.smal2.presenter.view.I_RegisterUserView;
import org.smal2.service.UserService;
import org.smal2.service.user.RegisterUserRequest;
import org.smal2.service.user.UserType;
import org.smal2.test.presenter.mock.RegisterUserViewMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class RegisterUserPresenterTest {

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
	public void registerUserMustSaveUser() {
		// Arrange
		userRepository.insert(new User("0001", "Jhon", new Date()));
		userRepository.insert(new User("0002", "Jack", new Date()));
		userRepository.insert(new User("0003", "Joe", new Date()));

		// Act
		I_RegisterUserView view = new RegisterUserViewMock();
		new RegisterUserPresenter(view, userService);
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();
		view.setRequest(new RegisterUserRequest("0004", "Jessy", birthDate,
				UserType.STUDENT));
		view.getRegisterUserCommand().execute();

		// Assert
		Assert.assertEquals(4, userRepository.listAll().size());
		Assert.assertEquals("0004", userRepository.getByRegistration("0004")
				.getRegistration());
		Assert.assertEquals("Jessy", userRepository.getByRegistration("0004")
				.getName());
		Assert.assertEquals(birthDate, userRepository.getByRegistration("0004")
				.getBirthDate());
		// TODO [CMP] gettype
	}
}
