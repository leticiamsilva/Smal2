package org.smal2.service.computer;

public class RegisterComputerRequest {

	private String asset_code;
	private String laboratory_name;
	private int row_num;
	private int column_num;

	public RegisterComputerRequest() {
	}

	public RegisterComputerRequest(String asset_code, String laboratory_name,
			int row_num, int column_num) {
		this.asset_code = asset_code;
		this.laboratory_name = laboratory_name;
		this.row_num = row_num;
		this.column_num = column_num;
	}

	public String getAsset_code() {
		return asset_code;
	}

	public String getLaboratory_name() {
		return laboratory_name;
	}

	public int getRow_num() {
		return row_num;
	}

	public int getColumn_num() {
		return column_num;
	}
}
