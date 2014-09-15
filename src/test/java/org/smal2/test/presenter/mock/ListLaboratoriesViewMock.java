package org.smal2.test.presenter.mock;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.ListLaboratoriesResponse;

public class ListLaboratoriesViewMock implements IListLaboratoriesView {

	private ICommand command;
	private ListLaboratoriesResponse response;

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public ListLaboratoriesResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListLaboratoriesResponse response) {
		this.response = response;
	}
}
