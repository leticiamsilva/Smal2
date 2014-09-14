package org.smal2.test.presenter;

import java.util.Date;
import java.util.GregorianCalendar;

import org.smal2.domain.entity.User;
import org.smal2.domain.repository.UserRepository;
import org.smal2.presentation.presenter.RegisterPrivilegedUserPresenter;
import org.smal2.presentation.view.IRegisterPrivilegedUserView;
import org.smal2.service.user.RegisterPrivilegedUserRequest;
import org.smal2.service.user.UserService;
import org.smal2.service.user.UserType;
import org.smal2.test.presenter.mock.RegisterPrivilegedUserViewMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RegisterPrivilegedUserPresenterTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Before
	public void before() {
		userRepository.insert(new User("0001", "Jhon", new Date()));
		userRepository.insert(new User("0002", "Jack", new Date()));
		userRepository.insert(new User("0003", "Joe", new Date()));
	}

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(userRepository);
		Assert.assertNotNull(userService);
	}

	@Test
	public void registerAdminMustSaveAdmin() {
		// Arrange
		IRegisterPrivilegedUserView view = new RegisterPrivilegedUserViewMock();
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		new RegisterPrivilegedUserPresenter(view, userService);
		view.setRequest(new RegisterPrivilegedUserRequest("0004", "password",
				"Jessy", birthDate, UserType.ADMINISTRATOR));
		view.getCommand().execute();

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

	@Test(expected = IllegalArgumentException.class)
	public void registerStudentServiceMustThrowException() {
		// Arrange
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		userService.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
				"0005", "password", "Jimmy", birthDate, UserType.STUDENT));
	}

	@Test
	public void registerStudentServiceMustShowError() {
		// Arrange
		IRegisterPrivilegedUserView view = new RegisterPrivilegedUserViewMock();
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		new RegisterPrivilegedUserPresenter(view, userService);
		view.setRequest(new RegisterPrivilegedUserRequest("0005", "password",
				"Jimmy", birthDate, UserType.STUDENT));
		view.getCommand().execute();

		// Assert
		Assert.assertTrue(view.getResponse().contains("User register error"));
	}
}
