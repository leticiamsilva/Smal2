package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListTicketsView;
import org.smal2.service.ticket.ListTicketsRequest;
import org.smal2.service.ticket.ListTicketsResponse;

public class ListTicketsViewMock implements IListTicketsView {

	private ListTicketsRequest request;
	private ICommand command;
	private String error;
	private ListTicketsResponse response;

	@Override
	public ListTicketsRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(ListTicketsRequest request) {
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
	public ListTicketsResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListTicketsResponse response) {
		this.response = response;
	}
}
