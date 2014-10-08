package org.smal2.persistence;

import java.util.List;

import org.smal2.domain.entity.Ticket;

public interface ITicketDAO {

	void create(Ticket entity);

	Ticket read(Long protocol);

	List<Ticket> readAll();

	void update(Ticket entity);

	void delete(Long protocol);

	boolean existWithProtocol(Long protocol);
}
