package org.smal2.service.computer;

public class RegisterComputerRequest {

	private String assetCode;
	private String laboratory;
	private int rowNum;
	private int columnNum;

	public RegisterComputerRequest() {
	}

	public RegisterComputerRequest(String assetCode, String laboratory,
			int rowNum, int columnNum) {
		this.assetCode = assetCode;
		this.laboratory = laboratory;
		this.rowNum = rowNum;
		this.columnNum = columnNum;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public String getLaboratory() {
		return laboratory;
	}

	public int getRowNum() {
		return rowNum;
	}

	public int getColumnNum() {
		return columnNum;
	}
}
