package org.smal2.presentation.view.util;

public interface IGenericResponseView<RES> extends ISimpleActionView {
	RES getResponse();

	void setResponse(RES response);
}
