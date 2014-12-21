package org.smal2.domain.repository;

import java.util.List;

import org.smal2.domain.entity.Ticket;
import org.smal2.persistence.ITicketDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketRepository {

	@Autowired
	private ITicketDAO dao;

	public void insert(Ticket entity) {
		dao.create(entity);
	}

	public Ticket get(long protocol) {
		return dao.read(protocol);
	}

	public List<Ticket> listAll() {
		return dao.readAll();
	}

	public void save(Ticket entity) {
		dao.update(entity);
	}

	public void remove(long protocol) {
		dao.delete(protocol);
	}

	public boolean existWithProtocol(long protocol) {
		return dao.existWithProtocol(protocol);
	}
}
