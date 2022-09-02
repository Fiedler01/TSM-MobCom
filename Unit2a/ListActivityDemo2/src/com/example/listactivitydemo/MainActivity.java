package com.example.listactivitydemo;

import android.app.ListActivity;
import android.os.Bundle;

/*
 * Implements a very simple list activity for demo purposes.
 *
 * ListActivity is deprecated in API 30 but it is important to understand
 * how it works for the objectives of this lecture.
 * It can be replaced by androidx.ListFragment.
 */
public class MainActivity extends ListActivity {
	public void onCreate(Bundle icicle) {
		final int numRows = 64;
		super.onCreate(icicle);
		RowData[] rowData = new RowData[numRows];

		// initialize the array with some data (for demo and debugging purposes
		// only)
		for (int i = 0; i < numRows; i++) {
			int iconId = R.drawable.ic_action_share; // default icon
			if (i % 3 == 0) {
				iconId = R.drawable.ic_action_delete; // icon for 3rd, 6th, 9th,
														// etc.
			}
			rowData[i] = new RowData("Item " + String.valueOf(i + 1), iconId);
		}

		// construct and register the adapter
		MySimpleExtendedArrayAdapter adapter = new MySimpleExtendedArrayAdapter(
				this, rowData);
		setListAdapter(adapter);
	}
}