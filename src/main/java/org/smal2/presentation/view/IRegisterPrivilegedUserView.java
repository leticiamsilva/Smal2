package org.smal2.presentation.view;

import org.smal2.common.ICommand;
import org.smal2.service.user.RegisterPrivilegedUserRequest;

public interface IRegisterPrivilegedUserView {

	RegisterPrivilegedUserRequest getRequest();

	void setRequest(RegisterPrivilegedUserRequest request);

	ICommand getCommand();

	void setCommand(ICommand command);

	String getResponse();

	void setResponse(String response);
}
