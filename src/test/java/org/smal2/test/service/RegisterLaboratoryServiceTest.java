package org.smal2.test.service;

import org.junit.Assert;
import org.junit.Test;

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
