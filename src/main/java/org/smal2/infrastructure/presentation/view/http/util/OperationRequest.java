package org.smal2.infrastructure.presentation.view.http.util;

public class OperationRequest<T> {
	private T request;

	public T getRequest() {
		return request;
	}

	public void setRequest(T request) {
		this.request = request;
	}
}
