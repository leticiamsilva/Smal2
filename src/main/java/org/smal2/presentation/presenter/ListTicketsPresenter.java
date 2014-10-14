package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListTicketsView;
import org.smal2.service.ticket.TicketService;

public class ListTicketsPresenter {

	private IListTicketsView view;
	private TicketService service;

	public ListTicketsPresenter(IListTicketsView view, TicketService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doListTickets();
			}
		});
	}

	private void doListTickets() {
		try {
			view.setResponse(service.listTickets());
		} catch (Exception ex) {
			view.setError("List tickets error:\n" + ex.getMessage());
		}
	}
}
