package org.smal2.presentation.view.util;

import org.smal2.common.ICommand;

public interface IGenericView<REQ, RES> {

	REQ getRequest();

	void setRequest(REQ request);

	ICommand getCommand();

	void setCommand(ICommand command);

	RES getResponse();

	void setResponse(RES response);

	String getError();

	void setError(String errorMessage);
}
