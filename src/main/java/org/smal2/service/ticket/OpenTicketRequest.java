package org.smal2.service.ticket;

public class OpenTicketRequest {

	private String registration;
	private String assetCode;
	private String subTrouble;
	private String description;

	public OpenTicketRequest() {
	}

	public OpenTicketRequest(String registration, String assetCode,
			String subTrouble, String description) {
		this.registration = registration;
		this.assetCode = assetCode;
		this.subTrouble = subTrouble;
		this.description = description;
	}

	public String getRegistration() {
		return registration;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public String getSubTrouble() {
		return subTrouble;
	}

	public String getDescription() {
		return description;
	}
}
