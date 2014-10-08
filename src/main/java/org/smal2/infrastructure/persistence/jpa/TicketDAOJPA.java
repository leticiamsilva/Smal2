package org.smal2.infrastructure.persistence.jpa;

import java.util.List;

import org.smal2.domain.entity.Ticket;
import org.smal2.persistence.ITicketDAO;
import org.springframework.stereotype.Component;

@Component
public class TicketDAOJPA extends GenericDAOJPA<Ticket> implements ITicketDAO {

	@Override
	public Ticket read(Long protocol) {
		return read(Ticket.class, protocol);
	}

	@Override
	public List<Ticket> readAll() {
		return readAll(Ticket.class);
	}

	@Override
	public void delete(Long protocol) {
		delete(Ticket.class, protocol);
	}

	@Override
	public boolean existWithProtocol(Long protocol) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT e FROM Ticket e WHERE e.protocol = ?");
		Object array[] = { protocol };

		return super.hasEntity(query.toString(), array);
	}
}