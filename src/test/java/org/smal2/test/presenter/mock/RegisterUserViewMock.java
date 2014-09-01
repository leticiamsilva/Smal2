package org.smal2.test.presenter.mock;

import org.smal2.common.I_Command;
import org.smal2.presenter.view.I_RegisterUserView;
import org.smal2.service.user.RegisterUserRequest;

public class RegisterUserViewMock implements I_RegisterUserView {

	private RegisterUserRequest request;
	private I_Command command;
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
	public I_Command getRegisterUserCommand() {
		return command;
	}

	@Override
	public void setCommand(I_Command command) {
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
