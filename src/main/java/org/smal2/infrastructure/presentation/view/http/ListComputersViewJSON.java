package org.smal2.infrastructure.presentation.view.http;

import org.smal2.common.ICommand;
import org.smal2.infrastructure.presentation.view.http.util.JSONResponse;
import org.smal2.presentation.presenter.ListComputersPresenter;
import org.smal2.presentation.view.IListComputersView;
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
public class ListComputersViewJSON implements IListComputersView {
	@Autowired
	private ComputerService computerService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public JSONResponse listComputers(@RequestBody String request) {
		try {
			this.request = request;
			new ListComputersPresenter(this, computerService);

			return new JSONResponse(true, response);

		} catch (Exception ex) {
			return new JSONResponse(false, "Error:\n" + ex.getMessage());
		}
	}

	// IListComputersView implementation

	private String request;
	private ICommand command;
	private ListComputersResponse response;

	@Override
	public String getRequest() {
		return request;
	}

	@Override
	public void setRequest(String request) {
		this.request = request;
	}

	@Override
	public ICommand getCommand() {
		return command;
	}

	@Override
	public void setCommand(ICommand command) {
		this.command = command;
	}

	@Override
	public ListComputersResponse getResponse() {
		return response;
	}

	@Override
	public void setResponse(ListComputersResponse computers) {
		this.response = computers;
	}

	// end IListComputersView
}
