package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IOpenTicketView;
import org.smal2.service.ticket.TicketService;

public class OpenTicketPresenter {

	private IOpenTicketView view;
	private TicketService service;

	public OpenTicketPresenter(IOpenTicketView view, TicketService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doOpenTicket();
			}
		});
	}

	private void doOpenTicket() {
		try {
			view.setResponse(service.openTicket(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Open ticket error:\n" + ex.getMessage());
		}
	}
}
