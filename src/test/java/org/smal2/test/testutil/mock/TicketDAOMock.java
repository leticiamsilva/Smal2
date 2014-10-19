package org.smal2.test.testutil.mock;

import java.lang.reflect.Field;

import org.smal2.domain.entity.Ticket;
import org.smal2.persistence.ITicketDAO;
import org.springframework.stereotype.Component;

@Component
public class TicketDAOMock extends AInMemoryDAO<Ticket, Long> implements
		ITicketDAO {

	private static long protocol = 0;

	@Override
	public void create(Ticket entity) {
		++protocol;

		Field privateStringField;
		try {
			privateStringField = Ticket.class.getDeclaredField("protocol");
			privateStringField.setAccessible(true);
			privateStringField.set(entity, protocol);
		} catch (Exception e) {
			e.printStackTrace();
		}

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