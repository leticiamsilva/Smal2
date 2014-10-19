package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IAssignTicketView;
import org.smal2.service.ticket.TicketService;

public class AssignTicketPresenter {

	private IAssignTicketView view;
	private TicketService service;

	public AssignTicketPresenter(IAssignTicketView view, TicketService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doAssignTicket();
			}
		});
	}

	private void doAssignTicket() {
		try {
			view.setResponse(service.assignTicket(view.getRequest()));
		} catch (Exception ex) {
			view.setError("Assign ticket error:\n" + ex.getMessage());
		}
	}
}
