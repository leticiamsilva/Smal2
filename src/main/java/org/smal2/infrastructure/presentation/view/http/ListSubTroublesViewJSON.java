package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.ListSubTroublesPresenter;
import org.smal2.presentation.view.IListSubTroublesView;
import org.smal2.presentation.view.basic.ListSubTroublesViewMock;
import org.smal2.service.subtrouble.ListSubTroublesResponse;
import org.smal2.service.subtrouble.SubTroubleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/subtrouble")
public class ListSubTroublesViewJSON {
	@Autowired
	private SubTroubleService subTroubleService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<ListSubTroublesResponse> listSubTroubles(
			@RequestBody OperationRequest<String> request) {
		OperationResponse<ListSubTroublesResponse> response = new OperationResponse<ListSubTroublesResponse>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IListSubTroublesView view = new ListSubTroublesViewMock();

			view.setRequest(request.getRequest());
			new ListSubTroublesPresenter(view, subTroubleService);
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
