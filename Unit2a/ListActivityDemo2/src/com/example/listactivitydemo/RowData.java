package com.example.listactivitydemo;

/*
 * data of one row
 */
public class RowData {
	String string;
	int imageId;

	RowData(String string, int imageId) {
		this.string = string;
		this.imageId = imageId;
	}

	public String toString() {
		return this.string;
	}
}
