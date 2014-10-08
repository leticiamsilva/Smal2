package org.smal2.service.ticket;

public class OpenTicketRequest {

	private String registration;
	private String asset_code;
	private String sub_trouble;
	private String description;

	public OpenTicketRequest() {
	}

	public OpenTicketRequest(String registration, String asset_code,
			String sub_trouble, String description) {
		this.registration = registration;
		this.asset_code = asset_code;
		this.sub_trouble = sub_trouble;
		this.description = description;
	}

	public String getRegistration() {
		return registration;
	}

	public String getAsset_code() {
		return asset_code;
	}

	public String getSub_trouble() {
		return sub_trouble;
	}

	public String getDescription() {
		return description;
	}
}
