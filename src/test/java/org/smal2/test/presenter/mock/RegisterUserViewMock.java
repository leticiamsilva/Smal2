package org.smal2.test.presenter.mock;

import org.smal2.common.ICommand;
import org.smal2.presenter.view.IRegisterUserView;
import org.smal2.service.user.RegisterUserRequest;

public class RegisterUserViewMock implements IRegisterUserView {

	private RegisterUserRequest request;
	private ICommand command;
	private String response;

	@Override
	public RegisterUserRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(RegisterUserRequest request) {
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
