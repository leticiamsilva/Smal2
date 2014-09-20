package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.RegisterComputerPresenter;
import org.smal2.presentation.view.IRegisterComputerView;
import org.smal2.presentation.view.basic.RegisterComputerViewMock;
import org.smal2.service.computer.RegisterComputerRequest;
import org.smal2.service.computer.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/computer")
public class RegisterComputerViewJSON {
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<String> registerComputer(
			@RequestBody OperationRequest<RegisterComputerRequest> request) {
		OperationResponse<String> response = new OperationResponse<String>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IRegisterComputerView view = new RegisterComputerViewMock();

			view.setRequest(request.getRequest());
			new RegisterComputerPresenter(view, computerService);
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
