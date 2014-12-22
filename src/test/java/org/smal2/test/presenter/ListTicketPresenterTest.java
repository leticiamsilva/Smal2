package org.smal2.test.presenter;

import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.ListTicketsPresenter;
import org.smal2.presentation.view.IListTicketsView;
import org.smal2.presentation.view.basic.ListTicketsViewMock;
import org.smal2.service.ticket.OpenTicketRequest;
import org.smal2.test.testutil.ABaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListTicketPresenterTest extends ABaseTest {

	@Before
	public void before() {
		String registration = "registration01";
		User user = new User(registration, MD5Generator.generate("password"),
				"user");
		userRepository.insert(user);

		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(1, 1, lab);
		positionRepository.insert(pos);

		String assetCode = "asset01";
		computerRepository.insert(new Computer(assetCode, pos));

		Trouble trouble = new Trouble("trouble01");
		troubleRepository.insert(trouble);

		SubTrouble subTrouble = new SubTrouble("subTrouble01", trouble);
		subTroubleRepository.insert(subTrouble);

		String description = "descriptionEmpty";

		ticketService.openTicket(new OpenTicketRequest(registration, assetCode,
				trouble.getName(), subTrouble.getName(), description));
	}

	@Test
	public void listMustReturnAllTickets() {
		// Arrange
		IListTicketsView view = new ListTicketsViewMock();

		// Act
		new ListTicketsPresenter(view, ticketService);
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(1, view.getResponse().size());
	}
}
