package org.smal2.presentation.view;

import org.smal2.common.ICommand;

public interface IGenericView<REQ, RES> extends IGenericResponseView<RES> {

	REQ getRequest();

	void setRequest(REQ request);

	ICommand getCommand();

	void setCommand(ICommand command);
}
