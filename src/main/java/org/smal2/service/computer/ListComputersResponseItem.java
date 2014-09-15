package org.smal2.service.computer;

public class ListComputersResponseItem {
	private String assetCode;
	private int rowNum;
	private int columnNum;

	public ListComputersResponseItem(String assetCode, int rowNum, int columnNum) {
		this.assetCode = assetCode;
		this.rowNum = rowNum;
		this.columnNum = columnNum;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public int getRowNum() {
		return rowNum;
	}

	public int getColumnNum() {
		return columnNum;
	}
}
