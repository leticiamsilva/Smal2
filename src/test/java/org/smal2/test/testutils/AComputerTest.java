package org.smal2.test.testutils;

import org.smal2.domain.repository.ComputerRepository;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.domain.repository.PositionRepository;
import org.smal2.service.computer.ComputerService;
import org.smal2.service.laboratory.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
public abstract class AComputerTest {

	@Autowired
	protected ComputerRepository computerRepository;

	@Autowired
	protected LaboratoryRepository laboratoryRepository;

	@Autowired
	protected PositionRepository positionRepository;

	@Autowired
	protected ComputerService computerService;

	@Autowired
	protected LaboratoryService laboratoryService;

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(computerRepository);
		Assert.assertNotNull(laboratoryRepository);
		Assert.assertNotNull(positionRepository);
		Assert.assertNotNull(computerService);
		Assert.assertNotNull(laboratoryService);
	}
}
