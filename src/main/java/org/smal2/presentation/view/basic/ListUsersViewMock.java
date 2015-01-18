package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.service.user.ListUsersRequest;
import org.smal2.service.user.ListUsersResponse;

public class ListUsersViewMock implements IListUsersView {

	private ListUsersRequest request;
	private ICommand command;
	private String error;
	private ListUsersResponse response;

	@Override
	public ListUsersRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(ListUsersRequest request) {
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
	public ListUsersResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListUsersResponse response) {
		this.response = response;
	}
}
