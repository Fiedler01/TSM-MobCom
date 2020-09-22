package com.example.listactivitydemo;

/*
 * data of one row
 */
public class RowData {
	String string;
    boolean checked;

	public RowData(String string,boolean checked) {
		this.string = string;
        this.checked=checked;
	}

	public String toString() {
		return this.string;
	}

}
