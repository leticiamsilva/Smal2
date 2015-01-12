package org.smal2.service.ticket;

public class OpenTicketRequest {

	private String session_id;
	private String asset_code;
	private String trouble;
	private String sub_trouble;
	private String description;

	public OpenTicketRequest() {
	}

	public OpenTicketRequest(String session_id, String asset_code,
			String trouble, String sub_trouble, String description) {
		this.session_id = session_id;
		this.asset_code = asset_code;
		this.trouble = trouble;
		this.sub_trouble = sub_trouble;
		this.description = description;
	}

	public String getSession_id() {
		return session_id;
	}

	public String getAsset_code() {
		return asset_code;
	}

	public String getTrouble() {
		return trouble;
	}

	public String getSub_trouble() {
		return sub_trouble;
	}

	public String getDescription() {
		return description;
	}
}
