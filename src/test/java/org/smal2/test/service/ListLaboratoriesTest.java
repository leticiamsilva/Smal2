package org.smal2.test.service;

import org.smal2.domain.entity.Laboratory;
import org.smal2.service.laboratory.ListLaboratoriesResponse;
import org.smal2.service.laboratory.ListLaboratoriesResponseItem;
import org.smal2.test.service.ALaboratoryServiceTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListLaboratoriesTest extends ALaboratoryServiceTest {

	@Before
	public void before() {
		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
	}

	@Test
	public void listMustReturnAllLaboratories() {
		// Act
		ListLaboratoriesResponse response = laboratoryService
				.listLaboratories();

		// Assert
		Assert.assertEquals(2, response.size());

		ListLaboratoriesResponseItem r1, r2;

		if (response.get(0).getName() == "lab01") {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("lab01", r1.getName());
		Assert.assertEquals("lab02", r2.getName());
	}
}
