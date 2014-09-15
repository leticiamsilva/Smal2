package org.smal2.presentation.view.util;

public interface IGenericView<REQ, RES> extends IGenericResponseView<RES>,
		IGenericRequestView<REQ> {

	REQ getRequest();

	void setRequest(REQ request);
}
