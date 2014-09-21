package org.smal2.service.computer;

public class ListComputersResponseItem {
	private String asset_code;
	private int row_num;
	private int column_num;

	public ListComputersResponseItem(String asset_code, int row_num, int column_num) {
		this.asset_code = asset_code;
		this.row_num = row_num;
		this.column_num = column_num;
	}

	public String getAsset_code() {
		return asset_code;
	}

	public int getRow_num() {
		return row_num;
	}

	public int getColumn_num() {
		return column_num;
	}
}
