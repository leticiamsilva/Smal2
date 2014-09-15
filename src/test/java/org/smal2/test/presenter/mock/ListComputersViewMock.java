package org.smal2.test.presenter.mock;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListComputersView;
import org.smal2.service.computer.ListComputersResponse;

public class ListComputersViewMock implements IListComputersView {

	private String request;
	private ICommand command;
	private ListComputersResponse response;

	@Override
	public String getRequest() {
		return request;
	}

	@Override
	public void setRequest(String request) {
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
	public ListComputersResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListComputersResponse response) {
		this.response = response;
	}
}
