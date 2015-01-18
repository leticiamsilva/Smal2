package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.ListComputersPresenter;
import org.smal2.presentation.view.IListComputersView;
import org.smal2.presentation.view.basic.ListComputersViewMock;
import org.smal2.service.computer.ListComputersRequest;
import org.smal2.service.computer.ListComputersResponse;
import org.smal2.service.computer.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/computer")
public class ListComputersViewJSON {
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<ListComputersResponse> listComputers(
			@RequestBody OperationRequest<ListComputersRequest> request) {
		OperationResponse<ListComputersResponse> response = new OperationResponse<ListComputersResponse>();

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IListComputersView view = new ListComputersViewMock();

			view.setRequest(request.getRequest());
			new ListComputersPresenter(view, computerService);
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
