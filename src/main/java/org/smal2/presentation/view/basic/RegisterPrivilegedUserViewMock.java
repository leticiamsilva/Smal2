package org.smal2.presentation.view.basic;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IRegisterPrivilegedUserView;
import org.smal2.service.user.RegisterPrivilegedUserRequest;

public class RegisterPrivilegedUserViewMock implements IRegisterPrivilegedUserView {

	private RegisterPrivilegedUserRequest request;
	private ICommand command;
	private String error;
	private String response;

	@Override
	public RegisterPrivilegedUserRequest getRequest() {
		return request;
	}

	@Override
	public void setRequest(RegisterPrivilegedUserRequest request) {
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
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}
}
