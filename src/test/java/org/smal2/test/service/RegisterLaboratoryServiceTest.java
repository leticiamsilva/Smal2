package org.smal2.test.service;

import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testContext.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class RegisterLaboratoryServiceTest extends ALaboratoryServiceTest {

	@Test(expected = IllegalArgumentException.class)
	public void registerNullLaboratoryNameMustThrowException() {
		// Act
		laboratoryService.registerLaboratory(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerEmptyLaboratoryNameMustThrowException() {
		// Act
		laboratoryService.registerLaboratory("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void registerExistentLaboratoryServiceMustThrowException() {
		// Act
		laboratoryService.registerLaboratory("lab01");
	}

	@Test
	public void registerNewLaboratoryNameMustRegister() {
		// Arrange
		String name = "lab04";

		// Act
		laboratoryService.registerLaboratory(name);

		// Assert
		Assert.assertEquals(name, laboratoryRepository.getByName(name)
				.getName());
		Assert.assertEquals(4, laboratoryRepository.listAll().size());
	}
}
