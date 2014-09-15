package org.smal2.test.presenter.mock;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterComputerView;
import org.smal2.service.computer.RegisterComputerRequest;

public class RegisterComputerViewMock implements IRegisterComputerView {

	private RegisterComputerRequest request;
	private ICommand command;
	private String response;

	@Override
	public RegisterComputerRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(RegisterComputerRequest request) {
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
