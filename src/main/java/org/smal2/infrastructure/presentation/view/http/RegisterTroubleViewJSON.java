package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.RegisterTroublePresenter;
import org.smal2.presentation.view.IRegisterTroubleView;
import org.smal2.presentation.view.basic.RegisterTroubleViewMock;
import org.smal2.service.trouble.TroubleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/trouble")
public class RegisterTroubleViewJSON {
	@Autowired
	private TroubleService troubleService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<String> registerTrouble(
			@RequestBody OperationRequest<String> request) {
		OperationResponse<String> response = new OperationResponse<String>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IRegisterTroubleView view = new RegisterTroubleViewMock();

			view.setRequest(request.getRequest());
			new RegisterTroublePresenter(view, troubleService);
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
