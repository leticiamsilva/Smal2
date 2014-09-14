package org.smal2.test.presenter;

import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.service.laboratory.LaboratoryService;
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
public abstract class ALaboratoriesPresenterTest {

	@Autowired
	protected LaboratoryRepository laboratoryRepository;

	@Autowired
	protected LaboratoryService laboratoryService;

	@Before
	public void before() {
		laboratoryRepository.insert(new Laboratory("lab01"));
		laboratoryRepository.insert(new Laboratory("lab02"));
		laboratoryRepository.insert(new Laboratory("lab03"));
	}

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(laboratoryRepository);
		Assert.assertNotNull(laboratoryService);
	}
}
