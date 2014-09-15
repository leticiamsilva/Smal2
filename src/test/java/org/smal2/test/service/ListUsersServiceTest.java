package org.smal2.test.service;

import java.util.Date;
import java.util.GregorianCalendar;

import org.smal2.domain.entity.Administrator;
import org.smal2.domain.entity.User;
import org.smal2.service.user.ListUsersResponse;
import org.smal2.service.user.ListUsersResponseItem;
import org.smal2.test.testutils.AUserTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListUsersServiceTest extends AUserTest {

	private Date birthDate1 = new GregorianCalendar(2001, 01, 01).getTime();
	private Date birthDate2 = new GregorianCalendar(2001, 01, 02).getTime();

	@Before
	public void before() {
		userRepository.insert(new User("0001", "Jhon", birthDate1));
		userRepository.insert(new Administrator("0002", "Jack", birthDate2,
				"pass"));
	}

	@Test
	public void listMustReturnAllUsers() {
		// Act
		ListUsersResponse response = userService.listUsers();

		// Assert
		Assert.assertEquals(2, response.size());

		ListUsersResponseItem r1, r2;

		if (response.get(0).getRegistration() == "0001") {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("0001", r1.getRegistration());
		Assert.assertEquals("Jhon", r1.getName());
		// TODO [CMP] to test
		// Assert.assertEquals(UserType.STUDENT, r1.getType());
		Assert.assertEquals("0002", r2.getRegistration());
		Assert.assertEquals("Jack", r2.getName());
		// TODO [CMP] to test
		// Assert.assertEquals(UserType.ADMINISTRATOR, r2.getType());
	}
}
