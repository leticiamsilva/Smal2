package org.smal2.presentation.view.util;

public interface IGenericRequestView<REQ> extends ISimpleActionView {
	REQ getRequest();

	void setRequest(REQ request);
}
