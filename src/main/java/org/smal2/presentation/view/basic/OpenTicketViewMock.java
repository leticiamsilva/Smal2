package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IOpenTicketView;
import org.smal2.service.ticket.OpenTicketRequest;

public class OpenTicketViewMock implements IOpenTicketView {

	private OpenTicketRequest request;
	private ICommand command;
	private String error;
	private String response;

	@Override
	public OpenTicketRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(OpenTicketRequest request) {
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
