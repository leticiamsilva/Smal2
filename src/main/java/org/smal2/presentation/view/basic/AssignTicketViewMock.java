package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IAssignTicketView;
import org.smal2.service.ticket.AssignTicketRequest;

public class AssignTicketViewMock implements IAssignTicketView {

	private AssignTicketRequest request;
	private ICommand command;
	private String error;
	private String response;

	@Override
	public AssignTicketRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(AssignTicketRequest request) {
		this.request = request;
	}

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public String getError() {
		return error;
	}

	@Override
	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}
}
