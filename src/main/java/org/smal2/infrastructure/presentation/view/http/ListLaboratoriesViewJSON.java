package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.ListLaboratoriesPresenter;
import org.smal2.presentation.view.IListLaboratoriesView;
import org.smal2.presentation.view.basic.ListLaboratoriesViewMock;
import org.smal2.service.laboratory.ListLaboratoriesResponse;
import org.smal2.service.laboratory.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/laboratory")
public class ListLaboratoriesViewJSON {
	@Autowired
	private LaboratoryService laboratoryService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<ListLaboratoriesResponse> listLaboratories(
			@RequestBody OperationRequest<String> request) {
		OperationResponse<ListLaboratoriesResponse> response = new OperationResponse<ListLaboratoriesResponse>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IListLaboratoriesView view = new ListLaboratoriesViewMock();

			new ListLaboratoriesPresenter(view, laboratoryService);
			view.getCommand().execute();

			if (view.getError() != null) {
				response.setError(view.getError());
			} else {
				response.setResponse(view.getResponse());
			}
		} catch (Exception ex) {
			response.setError("Unexpected error:\n" + ex.getMessage());
		}

		return response;
	}
}
