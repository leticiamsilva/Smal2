package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListTroublesView;
import org.smal2.service.trouble.TroubleService;

public class ListTroublesPresenter {

	private IListTroublesView view;
	private TroubleService service;

	public ListTroublesPresenter(IListTroublesView view, TroubleService service) {
		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doListTroubles();
			}
		});
	}

	public void doListTroubles() {
		try {
			view.setResponse(service.listTroubles(view.getRequest()));
		} catch (Exception ex) {
			view.setError("List troubles error:\n" + ex.getMessage());
		}
	}
}
