package org.smal2.presentation.view.util;

import org.smal2.common.ICommand;

public interface ISimpleActionView {

	ICommand getCommand();

	void setCommand(ICommand command);

}
