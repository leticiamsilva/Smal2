package org.smal2.service.ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.smal2.domain.entity.Administrator;
import org.smal2.domain.entity.Computer;
import org.smal2.domain.entity.SubTrouble;
import org.smal2.domain.entity.Technician;
import org.smal2.domain.entity.Ticket;
import org.smal2.domain.entity.User;
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

		if (request.getRegistration() == null
				|| request.getRegistration().equals("")) {
			throw new IllegalArgumentException("Undefined user registration.");
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

		if (!userRepository.existWithRegistration(request.getRegistration())) {
			throw new IllegalArgumentException("User must exist.");
		}

		if (!computerRepository.existWithAssetCode(request.getAsset_code())) {
			throw new IllegalArgumentException("Computer must exist.");
		}

		if (!subTroubleRepository.existWithName(request.getSub_trouble())) {
			throw new IllegalArgumentException("Sub-trouble must exist.");
		}

		User user = userRepository.getByRegistration(request.getRegistration());

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

		if (request.getAdministrator() == null
				|| request.getAdministrator().equals("")) {
			throw new IllegalArgumentException(
					"Undefined administrator registration.");
		}

		if (!userRepository.existWithRegistration(request.getAdministrator())) {
			throw new IllegalArgumentException("Administrator must exist.");
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

		User admin = userRepository.getByRegistration(request
				.getAdministrator());

		if (admin.getClass() != Administrator.class) {
			throw new IllegalArgumentException(
					"Ticket cannot be assigned from a not administrator user.");
		}

		User tech = userRepository.getByRegistration(request.getTechnician());

		if (tech.getClass() != Technician.class
				&& tech.getClass() != Administrator.class) {
			throw new IllegalArgumentException(
					"Ticket cannot be assigned to a not administrator and not technician user.");
		}

		Ticket ticket = ticketRepository.get(request.getProtocol());
		ticket.assign((Administrator) admin, (Technician) tech);
		ticketRepository.save(ticket);

		return "Ticket assigned successfully.";
	}

	public String closeTicket(CloseTicketRequest request) {

		if (request == null) {
			throw new IllegalArgumentException("Undefined request.");
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

		if (tech.getClass() != Technician.class
				&& tech.getClass() != Administrator.class) {
			throw new IllegalArgumentException(
					"Ticket cannot be closed by a not administrator and not technician user.");
		}

		Ticket ticket = ticketRepository.get(request.getProtocol());
		ticket.close(request.getStatus());
		ticketRepository.save(ticket);

		return "Ticket closed successfully.";
	}
}
