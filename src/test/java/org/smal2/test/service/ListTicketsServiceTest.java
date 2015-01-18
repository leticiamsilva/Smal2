package org.smal2.test.service;

import java.util.Date;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Ticket;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.ticket.ListTicketsRequest;
import org.smal2.service.ticket.ListTicketsResponse;
import org.smal2.service.ticket.ListTicketsResponseItem;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListTicketsServiceTest extends ABaseTest {

	private String session_id;

	@Before
	public void before() {
		User user = new User("0000", MD5Generator.generate("pass"), "Jhon",
				org.smal2.domain.entity.UserType.TECHNICIAN);
		userRepository.insert(user);

		// Arrange
		session_id = authService
				.loginUser(new LoginUserRequest("0000", "pass"))
				.getSession_id();

		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(1, 1, lab);
		positionRepository.insert(pos);

		Computer computer = new Computer("asset01", pos);
		computerRepository.insert(computer);

		Trouble trouble = new Trouble("trouble01");
		troubleRepository.insert(trouble);

		SubTrouble subTrouble = new SubTrouble("subTrouble01", trouble);
		subTroubleRepository.insert(subTrouble);

		ticketRepository.insert(new Ticket(new Date(), "desc1", user,
				subTrouble, computer));
		ticketRepository.insert(new Ticket(new Date(), "desc2", user,
				subTrouble, computer));
	}

	@Test
	public void listMustReturnAllTickets() {
		// Act
		ListTicketsResponse response = ticketService
				.listTickets(new ListTicketsRequest(session_id));

		// Assert
		Assert.assertEquals(2, response.size());

		ListTicketsResponseItem r1, r2;

		if (response.get(0).getDescription().equals("desc1")) {
			r1 = response.get(0);
			r2 = response.get(1);
		} else {
			r1 = response.get(1);
			r2 = response.get(0);
		}

		Assert.assertEquals("desc1", r1.getDescription());
		Assert.assertEquals("desc2", r2.getDescription());
	}
}
