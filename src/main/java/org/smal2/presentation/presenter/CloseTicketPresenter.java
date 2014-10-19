package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.ICloseTicketView;
import org.smal2.service.ticket.TicketService;

public class CloseTicketPresenter {

	private ICloseTicketView view;
	private TicketService service;

	public CloseTicketPresenter(ICloseTicketView view, TicketService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doCloseTicket();
			}
		});
	}

	private void doCloseTicket() {
		try {
			view.setResponse(service.closeTicket(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Close ticket error:\n" + ex.getMessage());
		}
	}
}
