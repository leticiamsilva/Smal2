package org.smal2.test.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.smal2.domain.repository.UserRepository;
import org.smal2.persistence.UserDAO;
import org.smal2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
public class JUnitSpringTest {

	@Autowired
	private UserDAO userDao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void mustAutowireUserDao() {
		Assert.assertNotNull(userDao);
	}

	@Test
	public void mustAutowireUserRepository() {
		Assert.assertNotNull(userRepository);
	}

	@Test
	public void mustAutowireUserService() {
		Assert.assertNotNull(userService);
	}
}