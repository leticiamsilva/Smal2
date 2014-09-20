package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.ListLaboratoriesResponse;

public class ListLaboratoriesViewMock implements IListLaboratoriesView {

	private ICommand command;
	private String error;
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
	public String getError() {
		return error;
	}

	@Override
	public void setError(String error) {
		this.error = error;
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
