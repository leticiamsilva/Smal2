package org.smal2.test.presenter.mock;

import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.ListLaboratoriesResponse;

public class ListLaboratoriesViewMock implements IListLaboratoriesView {

	private ListLaboratoriesResponse response;

	@Override
	public ListLaboratoriesResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListLaboratoriesResponse response) {
		this.response = response;
	}
}
