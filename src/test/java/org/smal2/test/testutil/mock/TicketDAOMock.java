package org.smal2.test.testutil.mock;

import org.smal2.domain.entity.Ticket;
import org.smal2.persistence.ITicketDAO;
import org.springframework.stereotype.Component;

@Component
public class TicketDAOMock extends AInMemoryDAO<Ticket, Long> implements
		ITicketDAO {

	@Override
	public void create(Ticket entity) {
		createByKey(entity, entity.getProtocol());
	}

	@Override
	public void update(Ticket entity) {
		updateByKey(entity, entity.getProtocol());
	}

	@Override
	public boolean existWithProtocol(Long protocol) {
		for (Ticket ticket : readAll()) {
			if (ticket.getProtocol() == protocol) {

				return true;
			}
		}

		return false;
	}
}