package org.smal2.service.ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Ticket;
import org.smal2.domain.entity.User;
import org.smal2.domain.entity.UserType;
import org.smal2.domain.repository.ComputerRepository;
import org.smal2.domain.repository.SubTroubleRepository;
import org.smal2.domain.repository.TicketRepository;
import org.smal2.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private ComputerRepository computerRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubTroubleRepository subTroubleRepository;

	public String openTicket(OpenTicketRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getSession_id() == null
				|| request.getSession_id().equals("")) {
			throw new IllegalArgumentException("Undefined session identifier.");
		}

		if (!userRepository.existWithSessionId(request.getSession_id())) {
			throw new IllegalArgumentException("Invalid session identifier");
		}

		User user = userRepository.getBySessionId(request.getSession_id());

		if (user.getType() != UserType.ADMINISTRATOR
				&& user.getType() != UserType.TECHNICIAN
				&& user.getType() != UserType.STUDENT) {
			throw new IllegalArgumentException(
					"User must be at least a Student to perform this operation.");
		}

		if (request.getDescription() == null
				|| request.getDescription().equals("")) {
			throw new IllegalArgumentException("Undefined description.");
		}

		if (request.getAsset_code() == null
				|| request.getAsset_code().equals("")) {
			throw new IllegalArgumentException("Undefined computer asset code.");
		}

		if (request.getSub_trouble() == null
				|| request.getSub_trouble().equals("")) {
			throw new IllegalArgumentException("Undefined sub-t	rouble name.");
		}

		if (!computerRepository.existWithAssetCode(request.getAsset_code())) {
			throw new IllegalArgumentException("Computer must exist.");
		}

		if (!subTroubleRepository.existWithName(request.getSub_trouble())) {
			throw new IllegalArgumentException("Sub-trouble must exist.");
		}

		Computer computer = computerRepository.get(request.getAsset_code());
		SubTrouble subTrouble = subTroubleRepository.get(request
				.getSub_trouble());

		ticketRepository.insert(new Ticket(new Date(),
				request.getDescription(), user, subTrouble, computer));

		return "Ticket open successfully.";
	}

	public ListTicketsResponse listTickets() {

		List<ListTicketsResponseItem> tickets = new ArrayList<ListTicketsResponseItem>();
		ListTicketsResponseItem item;

		for (Ticket ticket : ticketRepository.listAll()) {
			item = new ListTicketsResponseItem(ticket.getProtocol(),
					ticket.getOpenDate(), ticket.getCloseDate(),
					ticket.getDescription(),
					ticket.getUser().getRegistration(),
					(ticket.getTechnician() == null) ? null : ticket
							.getTechnician().getRegistration(),
					(ticket.getAdministrator() == null) ? null : ticket
							.getAdministrator().getRegistration(),
					ticket.getStatus(), ticket.getSubTrouble().getTrouble()
							.getName(), ticket.getSubTrouble().getName(),
					ticket.getComputer().getAssetCode());
			tickets.add(item);
		}

		return new ListTicketsResponse(tickets);
	}

	public String assignTicket(AssignTicketRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getSession_id() == null
				|| request.getSession_id().equals("")) {
			throw new IllegalArgumentException("Undefined session identifier.");
		}

		if (!userRepository.existWithSessionId(request.getSession_id())) {
			throw new IllegalArgumentException("Invalid session identifier");
		}

		User admin = userRepository.getBySessionId(request.getSession_id());

		if (admin.getType() != UserType.ADMINISTRATOR) {
			throw new IllegalArgumentException(
					"Ticket cannot be assigned from a not administrator user.");
		}

		if (request.getTechnician() == null
				|| request.getTechnician().equals("")) {
			throw new IllegalArgumentException(
					"Undefined technician registration.");
		}

		if (!userRepository.existWithRegistration(request.getTechnician())) {
			throw new IllegalArgumentException("Technician must exist.");
		}

		if (!ticketRepository.existWithProtocol(request.getProtocol())) {
			throw new IllegalArgumentException("Ticket must exist.");
		}

		User tech = userRepository.getByRegistration(request.getTechnician());

		if (tech.getType() != UserType.TECHNICIAN
				&& tech.getType() != UserType.ADMINISTRATOR) {
			throw new IllegalArgumentException(
					"Ticket cannot be assigned to a not administrator and not technician user.");
		}

		Ticket ticket = ticketRepository.get(request.getProtocol());
		ticket.assign(admin, tech);
		ticketRepository.save(ticket);

		return "Ticket assigned successfully.";
	}

	public String closeTicket(CloseTicketRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
		}

		if (request.getSession_id() == null
				|| request.getSession_id().equals("")) {
			throw new IllegalArgumentException("Undefined session identifier.");
		}

		if (!userRepository.existWithSessionId(request.getSession_id())) {
			throw new IllegalArgumentException("Invalid session identifier");
		}

		User tech = userRepository.getBySessionId(request.getSession_id());

		if (tech.getType() != UserType.ADMINISTRATOR
				&& tech.getType() != UserType.TECHNICIAN) {
			throw new IllegalArgumentException(
					"Ticket cannot be closed by a not administrator and not technician user.");
		}

		if (!ticketRepository.existWithProtocol(request.getProtocol())) {
			throw new IllegalArgumentException("Ticket must exist.");
		}

		Ticket ticket = ticketRepository.get(request.getProtocol());
		ticket.close(request.getStatus());
		ticketRepository.save(ticket);

		return "Ticket closed successfully.";
	}
}
