package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.JSONResponse;
import org.smal2.presentation.presenter.ListLaboratoriesPresenter;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.service.laboratory.ListLaboratoriesResponse;
import org.smal2.service.laboratory.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/laboratory")
public class ListLaboratoriesViewJSON implements IListLaboratoriesView {
	@Autowired
	private LaboratoryService laboratoryService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse listLaboratories() {
		try {
			new ListLaboratoriesPresenter(this, laboratoryService);

			return new JSONResponse(true, response);

		} catch (Exception ex) {
			return new JSONResponse(false, "Error:\n" + ex.getMessage());
		}
	}

	// IListLaboratoriesView implementation

	private ListLaboratoriesResponse response;

	@Override
	public ListLaboratoriesResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListLaboratoriesResponse laboratories) {
		this.response = laboratories;
	}

	// end IListLaboratoriesView
}
