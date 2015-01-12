package org.smal2.test.presenter;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.smal2.common.MD5Generator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.Laboratory;
import org.smal2.domain.entity.Position;
import org.smal2.domain.entity.Status;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Ticket;
import org.smal2.domain.entity.Trouble;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.presentation.presenter.AssignTicketPresenter;
import org.smal2.presentation.view.IAssignTicketView;
import org.smal2.presentation.view.basic.AssignTicketViewMock;
import org.smal2.service.ticket.AssignTicketRequest;
import org.smal2.test.testutil.ABaseTest;

public class AssignTicketPresenterTest extends ABaseTest {

	private Ticket openTicket;
	private Ticket inProgressTicket;

	@Before
	public void before() {
		User admin = new User("registration01",
				MD5Generator.generate("password"), "admin",
				UserType.ADMINISTRATOR);
		userRepository.insert(admin);

		User tech = new User("registration02",
				MD5Generator.generate("password"), "tech", UserType.TECHNICIAN);
		userRepository.insert(tech);

		User user = new User("registration03",
				MD5Generator.generate("password"), "user", UserType.STUDENT);
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

		openTicket = new Ticket(new Date(), "description1", user, subTrouble,
				computer);
		ticketRepository.insert(openTicket);

		inProgressTicket = new Ticket(new Date(), "description2", user,
				subTrouble, computer);
		inProgressTicket.assign(admin, tech);
		ticketRepository.insert(inProgressTicket);
	}

	@Test
	public void assignTicketMustAssignFromAdminstratorToTheInformedTechnicianWithStatusInProgress() {
		// Arrange
		long protocol = openTicket.getProtocol();
		String admin = "registration01";
		String tech = "registration02";

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(admin, openTicket.getAdministrator()
				.getRegistration());
		Assert.assertEquals(tech, openTicket.getTechnician().getRegistration());
		Assert.assertEquals(Status.IN_PROGRESS, openTicket.getStatus());
		Assert.assertEquals("Ticket assigned successfully.", view.getResponse());
	}

	@Test
	public void assignTicketAsNotAdministratorMustShowError() {
		// Arrange
		long protocol = openTicket.getProtocol();
		String admin = "registration03";
		String tech = "registration02";
		Status status = openTicket.getStatus();

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(status, openTicket.getStatus());
		Assert.assertEquals(
				"Assign ticket error:\nTicket cannot be assigned from a not administrator user.",
				view.getError());
	}

	@Test
	public void assignTicketToNotTechnicianUserMustShowError() {
		// Arrange
		long protocol = openTicket.getProtocol();
		String admin = "registration01";
		String tech = "registration03";
		Status status = openTicket.getStatus();

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(status, openTicket.getStatus());
		Assert.assertEquals(
				"Assign ticket error:\nTicket cannot be assigned to a not administrator and not technician user.",
				view.getError());
	}

	@Test
	public void assignAlreadyAssignedTicketMustShowError() {
		// Arrange
		long protocol = inProgressTicket.getProtocol();
		String admin = "registration01";
		String tech = "registration02";
		Status status = inProgressTicket.getStatus();

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(status, inProgressTicket.getStatus());
		Assert.assertEquals(
				"Assign ticket error:\nTicket with status different then Open cannot be assigned.",
				view.getError());
	}

	@Test
	public void assignTicketWithInvalidProtocolMustShowError() {
		// Arrange
		long protocol = 0;
		String admin = "registration01";
		String tech = "registration02";
		Status status = openTicket.getStatus();

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(status, openTicket.getStatus());
		Assert.assertEquals("Assign ticket error:\nTicket must exist.",
				view.getError());
	}

	@Test
	public void assignTicketWithInvalidAdministratorMustShowError() {
		// Arrange
		long protocol = openTicket.getProtocol();
		String admin = "registration00";
		String tech = "registration02";
		Status status = openTicket.getStatus();

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(status, openTicket.getStatus());
		Assert.assertEquals("Assign ticket error:\nAdministrator must exist.",
				view.getError());
	}

	@Test
	public void assignTicketWithInvalidTechnicianMustShowError() {
		// Arrange
		long protocol = openTicket.getProtocol();
		String admin = "registration01";
		String tech = "registration00";
		Status status = openTicket.getStatus();

		IAssignTicketView view = new AssignTicketViewMock();

		// Act
		new AssignTicketPresenter(view, ticketService);
		view.setRequest(new AssignTicketRequest(protocol, admin, tech));
		view.getCommand().execute();

		// Assert
		Assert.assertEquals(status, openTicket.getStatus());
		Assert.assertEquals("Assign ticket error:\nTechnician must exist.",
				view.getError());
	}
}