package org.smal2.test.presenter.mock;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.service.user.ListUsersResponse;

public class ListUsersViewMock implements IListUsersView {

	private ICommand command;
	private ListUsersResponse response;

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
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
