package org.smal2.test.presenter.mock;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterLaboratoryView;

public class RegisterLaboratoryViewMock implements IRegisterLaboratoryView {

	private String request;
	private ICommand command;
	private String response;

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
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}
}
