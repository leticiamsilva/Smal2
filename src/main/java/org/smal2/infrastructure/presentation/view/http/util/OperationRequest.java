package org.smal2.infrastructure.presentation.view.http.util;

public class OperationRequest<T> {
	private String session_id;
	private T request;

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}
}
