package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IAuthUserView;
import org.smal2.service.auth.LoginUserRequest;
import org.smal2.service.auth.LoginUserResponse;

public class AuthUserViewMock implements IAuthUserView {

	private LoginUserRequest request;
	private ICommand command;
	private String error;
	private LoginUserResponse response;

	@Override
	public LoginUserRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(LoginUserRequest request) {
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
	public LoginUserResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(LoginUserResponse response) {
		this.response = response;
	}
}
