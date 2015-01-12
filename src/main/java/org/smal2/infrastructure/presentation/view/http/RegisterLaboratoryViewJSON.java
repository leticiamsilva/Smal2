package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.RegisterLaboratoryPresenter;
import org.smal2.presentation.view.IRegisterLaboratoryView;
import org.smal2.presentation.view.basic.RegisterLaboratoryViewMock;
import org.smal2.service.laboratory.LaboratoryService;
import org.smal2.service.laboratory.RegisterLaboratoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/laboratory")
public class RegisterLaboratoryViewJSON {
	@Autowired
	private LaboratoryService laboratoryService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<String> registerLaboratory(
			@RequestBody OperationRequest<RegisterLaboratoryRequest> request) {
		OperationResponse<String> response = new OperationResponse<String>();

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IRegisterLaboratoryView view = new RegisterLaboratoryViewMock();

			view.setRequest(request.getRequest());
			new RegisterLaboratoryPresenter(view, laboratoryService);
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
