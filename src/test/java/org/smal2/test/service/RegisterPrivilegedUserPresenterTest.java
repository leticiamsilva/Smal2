package org.smal2.test.service;

import java.util.Date;
import java.util.GregorianCalendar;

import org.smal2.domain.entity.User;
import org.smal2.service.user.RegisterPrivilegedUserRequest;
import org.smal2.service.user.UserType;
import org.smal2.test.service.AUserServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RegisterPrivilegedUserPresenterTest extends AUserServiceTest {

	@Before
	public void before() {
		userRepository.insert(new User("0001", "Jhon", new Date()));
		userRepository.insert(new User("0002", "Jack", new Date()));
		userRepository.insert(new User("0003", "Joe", new Date()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullUserMustThrowException() {
		// Act
		userService.registerPrivilegedUser(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerNullUserRegistrationMustThrowException() {
		// Arrange
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		userService.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
				null, "password", "Jimmy", birthDate, UserType.ADMINISTRATOR));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyUserRegistrationMustThrowException() {
		// Arrange
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		userService.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
				"", "password", "Jimmy", birthDate, UserType.ADMINISTRATOR));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerExistentUserRegistrationMustThrowException() {
		// Arrange
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		userService
				.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
						"0001", "password", "Jimmy", birthDate,
						UserType.ADMINISTRATOR));
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerStudentServiceMustThrowException() {
		// Arrange
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();

		// Act
		userService.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
				"0004", "password", "Jimmy", birthDate, UserType.STUDENT));
	}

	@Test
	public void registerNewUserNameMustRegister() {
		// Arrange
		String registration = "0004";
		String password = "password";
		String name = "Jimmy";
		Date birthDate = new GregorianCalendar(2001, 01, 01).getTime();
		UserType type = UserType.ADMINISTRATOR;

		// Act
		userService.registerPrivilegedUser(new RegisterPrivilegedUserRequest(
				registration, password, "Jimmy", birthDate, type));

		// Assert
		User user = userRepository.getByRegistration(registration);
		Assert.assertEquals(registration, user.getRegistration());
		// Assert.assertEquals(password, user.getPassword()); // TODO [CMP] to
		// test
		Assert.assertEquals(name, user.getName());
		Assert.assertEquals(birthDate, user.getBirthDate());
		// Assert.assertEquals(type, user.getType()); // TODO [CMP] to test
		Assert.assertEquals(4, userRepository.listAll().size());
	}
}
