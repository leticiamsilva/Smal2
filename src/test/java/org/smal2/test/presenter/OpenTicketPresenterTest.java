package org.smal2.test.presenter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.OpenTicketPresenter;
import org.smal2.presentation.view.IOpenTicketView;
import org.smal2.presentation.view.basic.OpenTicketViewMock;
import org.smal2.service.ticket.OpenTicketRequest;
import org.smal2.test.testutil.ABaseTest;

public class OpenTicketPresenterTest extends ABaseTest {

	@Before
	public void before() {
		User user = new User("registration01", "name01", new Date());
		userRepository.insert(user);

		Laboratory lab = new Laboratory("lab01");
		laboratoryRepository.insert(lab);

		Position pos = new Position(1, 1, lab);
		positionRepository.insert(pos);

		computerRepository.insert(new Computer("asset01", pos));

		Trouble trouble = new Trouble("trouble01");
		troubleRepository.insert(trouble);

		SubTrouble subTrouble = new SubTrouble("subTrouble01", trouble);
		subTroubleRepository.insert(subTrouble);
	}

	@Test
	public void openTicketMustCreateNewTicket() {
		// Arrange
		String registration = "registration01";
		String assetCode = "asset01";
		String trouble = "trouble01";
		String subTrouble = "subTrouble01";
		String description = "descriptionEmpty";

		IOpenTicketView view = new OpenTicketViewMock();

		// Act
		new OpenTicketPresenter(view, ticketService);
		view.setRequest(new OpenTicketRequest(registration, assetCode, trouble,
				subTrouble, description));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(1, ticketRepository.listAll().size());
		Assert.assertTrue(view.getResponse()
				.equals("Ticket open successfully."));
	}
}
