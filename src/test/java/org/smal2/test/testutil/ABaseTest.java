package org.smal2.test.testutil;

import org.smal2.domain.repository.ComputerRepository;
import org.smal2.domain.repository.LaboratoryRepository;
import org.smal2.domain.repository.PositionRepository;
import org.smal2.domain.repository.SubTroubleRepository;
import org.smal2.domain.repository.TicketRepository;
import org.smal2.domain.repository.TroubleRepository;
import org.smal2.domain.repository.UserRepository;
import org.smal2.service.computer.ComputerService;
import org.smal2.service.laboratory.LaboratoryService;
import org.smal2.service.ticket.TicketService;
import org.smal2.service.user.UserService;
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
public abstract class ABaseTest {

	@Autowired
	protected UserRepository userRepository;

	@Autowired
	protected UserService userService;

	@Autowired
	protected ComputerRepository computerRepository;

	@Autowired
	protected ComputerService computerService;

	@Autowired
	protected LaboratoryRepository laboratoryRepository;

	@Autowired
	protected LaboratoryService laboratoryService;

	@Autowired
	protected PositionRepository positionRepository;

	@Autowired
	protected TicketRepository ticketRepository;

	@Autowired
	protected TicketService ticketService;

	@Autowired
	protected TroubleRepository troubleRepository;

	@Autowired
	protected SubTroubleRepository subTroubleRepository;

	@Test
	public void mustAutowireTestDependencies() {
		Assert.assertNotNull(userRepository);
		Assert.assertNotNull(userService);
		Assert.assertNotNull(computerRepository);
		Assert.assertNotNull(computerService);
		Assert.assertNotNull(laboratoryRepository);
		Assert.assertNotNull(laboratoryService);
		Assert.assertNotNull(positionRepository);
		Assert.assertNotNull(ticketRepository);
		Assert.assertNotNull(ticketService);
		Assert.assertNotNull(troubleRepository);
		Assert.assertNotNull(subTroubleRepository);
	}
}
