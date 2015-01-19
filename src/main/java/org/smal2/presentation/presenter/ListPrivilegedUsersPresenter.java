package org.smal2.presentation.presenter;

import org.smal2.common.ICommand;
import org.smal2.presentation.view.IListUsersView;
import org.smal2.service.user.UserService;

public class ListPrivilegedUsersPresenter {

	private IListUsersView view;
	private UserService service;

	public ListPrivilegedUsersPresenter(IListUsersView view, UserService service) {

		this.view = view;
		this.service = service;

		this.view.setCommand(new ICommand() {
			public void execute() {
				doListUsers();
			}
		});
	}

	public void doListUsers() {
		try {
			view.setResponse(service.listPrivilegedUsers(view.getRequest()));
		} catch (Exception ex) {
			view.setError("List users error:\n" + ex.getMessage());
		}
	}
}
