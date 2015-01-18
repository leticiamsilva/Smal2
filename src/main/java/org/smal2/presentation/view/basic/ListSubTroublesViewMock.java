package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListSubTroublesView;
import org.smal2.service.subtrouble.ListSubTroublesRequest;
import org.smal2.service.subtrouble.ListSubTroublesResponse;

public class ListSubTroublesViewMock implements IListSubTroublesView {

	private ListSubTroublesRequest request;
	private ICommand command;
	private String error;
	private ListSubTroublesResponse response;

	@Override
	public ListSubTroublesRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(ListSubTroublesRequest request) {
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
	public ListSubTroublesResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListSubTroublesResponse response) {
		this.response = response;
	}
}
