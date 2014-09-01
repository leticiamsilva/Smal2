package org.smal2.presenter.view;

import org.smal2.common.I_Command;
import org.smal2.service.user.RegisterUserRequest;

public interface I_RegisterUserView {

	RegisterUserRequest getRequest();

	void setRequest(RegisterUserRequest request);

	I_Command getRegisterUserCommand();

	void setCommand(I_Command command);

	String getResponse();

	void setResponse(String response);
}
