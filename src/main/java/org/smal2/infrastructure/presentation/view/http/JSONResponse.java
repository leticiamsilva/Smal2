package org.smal2.infrastructure.presentation.view.http;

public class JSONResponse {

	private boolean success;
	private Object result;

	public JSONResponse(boolean success, Object result) {
		this.success = success;
		this.result = result;
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getResult() {
		return result;
	}
}
