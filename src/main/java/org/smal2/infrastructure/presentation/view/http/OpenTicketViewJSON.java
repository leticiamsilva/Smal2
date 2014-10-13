package org.smal2.infrastructure.presentation.view.http;

import org.smal2.infrastructure.presentation.view.http.util.OperationRequest;
import org.smal2.infrastructure.presentation.view.http.util.OperationResponse;
import org.smal2.presentation.presenter.OpenTicketPresenter;
import org.smal2.presentation.view.IOpenTicketView;
import org.smal2.presentation.view.basic.OpenTicketViewMock;
import org.smal2.service.ticket.OpenTicketRequest;
import org.smal2.service.ticket.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@RequestMapping("/ticket")
public class OpenTicketViewJSON {
	@Autowired
	private TicketService ticketService;

	@RequestMapping(value = "/open", method = RequestMethod.POST)
	@ResponseBody
	public OperationResponse<String> openTicket(
			@RequestBody OperationRequest<OpenTicketRequest> request) {
		OperationResponse<String> response = new OperationResponse<String>();

		// TODO [CMP] verify request.getSessionId() permission

		try {
			// [CMP] spring controllers are singleton (as common servlet)
			// so we can't implements IView because his properties are shared
			IOpenTicketView view = new OpenTicketViewMock();

			view.setRequest(request.getRequest());
			new OpenTicketPresenter(view, ticketService);
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
