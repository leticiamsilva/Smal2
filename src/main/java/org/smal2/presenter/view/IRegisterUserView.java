package org.smal2.presenter.view;

import org.smal2.common.ICommand;
import org.smal2.service.user.RegisterUserRequest;

public interface IRegisterUserView {

	RegisterUserRequest getRequest();

	void setRequest(RegisterUserRequest request);

	ICommand getCommand();

	void setCommand(ICommand command);

	String getResponse();

	void setResponse(String response);
}
