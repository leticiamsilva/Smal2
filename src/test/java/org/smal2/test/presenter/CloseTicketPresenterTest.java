package org.smal2.test.presenter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Administrator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.Status;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Technician;
import org.smal2.domain.entity.Ticket;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.presentation.presenter.CloseTicketPresenter;
import org.smal2.presentation.view.ICloseTicketView;
import org.smal2.presentation.view.basic.CloseTicketViewMock;
import org.smal2.service.ticket.CloseTicketRequest;
import org.smal2.test.testutil.ABaseTest;

public class CloseTicketPresenterTest extends ABaseTest {

	private Ticket inProgressTicket;
	private Ticket openTicket;

	@Before
	public void before() {
		Administrator admin = new Administrator("registration01",
				MD5Generator.generate("password"), "admin", "admin@smal.org");
		userRepository.insert(admin);

		Technician tech = new Technician("registration02",
				MD5Generator.generate("password"), "tech", "tech@smal.org");
		userRepository.insert(tech);

		User user = new User("registration03",
				MD5Generator.generate("password"), "user");
		userRepository.insert(user);

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

		inProgressTicket = new Ticket(new Date(), "description1", user,
				subTrouble, computer);
		inProgressTicket.assign(admin, tech);
		ticketRepository.insert(inProgressTicket);

		openTicket = new Ticket(new Date(), "description2", user, subTrouble,
				computer);
		ticketRepository.insert(openTicket);
	}

	@Test
	public void closeInProgressResolvedTicketAsTechnicianMustCloseTicketWithActualDate() {
		// Arrange
		long protocol = inProgressTicket.getProtocol();
		String tech = "registration02";
		Status status = Status.RESOLVED;

		ICloseTicketView view = new CloseTicketViewMock();

		// Act
		new CloseTicketPresenter(view, ticketService);
		view.setRequest(new CloseTicketRequest(protocol, tech, status));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(Status.RESOLVED, inProgressTicket.getStatus());
		Assert.assertEquals(new Date(), inProgressTicket.getCloseDate());
		Assert.assertEquals("Ticket closed successfully.", view.getResponse());
	}

	@Test
	public void closeInProgressNotResolvedTicketAsTechnicianMustCloseTicketWithActualDate() {
		// Arrange
		long protocol = inProgressTicket.getProtocol();
		String tech = "registration02";
		Status status = Status.NOT_RESOLVED;

		ICloseTicketView view = new CloseTicketViewMock();

		// Act
		new CloseTicketPresenter(view, ticketService);
		view.setRequest(new CloseTicketRequest(protocol, tech, status));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(Status.NOT_RESOLVED, inProgressTicket.getStatus());
		Assert.assertEquals(new Date(), inProgressTicket.getCloseDate());
		Assert.assertEquals("Ticket closed successfully.", view.getResponse());
	}

	@Test
	public void closeInProgressResolvedTicketAsAdministratorMustCloseTicketWithActualDate() {
		// Arrange
		long protocol = inProgressTicket.getProtocol();
		String admin = "registration01";
		Status status = Status.RESOLVED;

		ICloseTicketView view = new CloseTicketViewMock();

		// Act
		new CloseTicketPresenter(view, ticketService);
		view.setRequest(new CloseTicketRequest(protocol, admin, status));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(Status.RESOLVED, inProgressTicket.getStatus());
		Assert.assertEquals(new Date(), inProgressTicket.getCloseDate());
		Assert.assertEquals("Ticket closed successfully.", view.getResponse());
	}

	@Test
	public void closeInProgressResolvedTicketAsNotTechnicianAndNotAdministratorMustShowError() {
		// Arrange
		long protocol = inProgressTicket.getProtocol();
		String tech = "registration03";
		Status status = Status.RESOLVED;

		ICloseTicketView view = new CloseTicketViewMock();

		// Act
		new CloseTicketPresenter(view, ticketService);
		view.setRequest(new CloseTicketRequest(protocol, tech, status));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(Status.IN_PROGRESS, inProgressTicket.getStatus());
		Assert.assertEquals(null, inProgressTicket.getCloseDate());
		Assert.assertEquals(
				"Close ticket error:\nTicket cannot be closed by a not administrator and not technician user.",
				view.getError());
	}

	@Test
	public void closeOpenResolvedTicketAsTechnicianMustShowError() {
		// Arrange
		long protocol = openTicket.getProtocol();
		String tech = "registration02";
		Status status = Status.RESOLVED;

		ICloseTicketView view = new CloseTicketViewMock();

		// Act
		new CloseTicketPresenter(view, ticketService);
		view.setRequest(new CloseTicketRequest(protocol, tech, status));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(Status.OPEN, openTicket.getStatus());
		Assert.assertEquals(null, openTicket.getCloseDate());
		Assert.assertEquals(
				"Close ticket error:\nTicket with status different then 'In Progress' cannot be closed.",
				view.getError());
	}

	@Test
	public void closeInProgressOpenTicketAsTechnicianMustShowError() {
		// Arrange
		long protocol = inProgressTicket.getProtocol();
		String tech = "registration02";
		Status status = Status.OPEN;

		ICloseTicketView view = new CloseTicketViewMock();

		// Act
		new CloseTicketPresenter(view, ticketService);
		view.setRequest(new CloseTicketRequest(protocol, tech, status));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(Status.IN_PROGRESS, inProgressTicket.getStatus());
		Assert.assertEquals(null, inProgressTicket.getCloseDate());
		Assert.assertEquals(
				"Close ticket error:\nTicket cannot be closed with final status different then 'Resolved' and 'Not Resloved'.",
				view.getError());
	}
}
