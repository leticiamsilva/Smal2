package org.smal2.infrastructure.presentation.view.http;

import org.smal2.common.ICommand;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.RegisterPrivilegedUserPresenter;
import org.smal2.presentation.view.IRegisterPrivilegedUserView;
import org.smal2.service.user.RegisterPrivilegedUserRequest;
import org.smal2.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/user")
public class RegisterPrivilegedUserViewJSON implements
		IRegisterPrivilegedUserView {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ResponseBody
	public OperationResponse<String> registerPrivilegedUser(
			@RequestBody RegisterPrivilegedUserRequest request) {
		OperationResponse<String> response = new OperationResponse<String>();

		try {
			this.request = request;
			new RegisterPrivilegedUserPresenter(this, userService);
			command.execute();
			response.setResponse(this.response);

		} catch (Exception ex) {
			response.setError("Error:\n" + ex.getMessage());
		}

		return response;
	}

	// IRegisterPrivilegedUserView implementation

	private RegisterPrivilegedUserRequest request;
	private ICommand command;
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
	public String getResponse() {
		return response;
	}

	@Override
	public void setResponse(String response) {
		this.response = response;
	}

	// end IRegisterPrivilegedUserView
}
