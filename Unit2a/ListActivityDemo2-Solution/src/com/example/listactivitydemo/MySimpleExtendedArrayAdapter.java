package com.example.listactivitydemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class MySimpleExtendedArrayAdapter extends ArrayAdapter<RowData> {
	private final Context context;
	private final RowData[] rowData;

	/*
	 * Adapter to fetch the strings and images for each row Make sure you store
	 * any per-item state in this adapter, not in the Views which may be
	 * recycled upon scrolling
	 */
	public MySimpleExtendedArrayAdapter(Context context, RowData[] rowData) {
		super(context, R.layout.rowlayout, rowData);
		this.context = context;
		this.rowData = rowData;
	}

	/*
	 * Get a View that displays the data at the specified position in the data
	 * set.
	 * 
	 * @param position -- The position of the item within the adapter's data set
	 * of the item whose view we want.
	 * 
	 * @param convertView -- The old view to reuse, if possible. Note: You
	 * should check that this view is non-null and of an appropriate type before
	 * using. If it is not possible to convert this view to display the correct
	 * data, this method can create a new view.
	 * 
	 * @param parent -- The parent that this view will eventually be attached
	 * to.
	 * 
	 * @return -- A View corresponding to the data at the specified position.
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		if (convertView == null) { // there is no convertView available from
									// scrolling that can be reused
			// obtain a rowView by instantiating the row layout XML file
			LayoutInflater vi = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (vi != null) {
				rowView = vi.inflate(R.layout.rowlayout, parent, false);
			}
		}

		// fill the rowView with data
		if (rowData[position] != null) {
			// obtain references to the views inside the rowView that we'd like
			// to fill with data
			TextView textView = rowView.findViewById(R.id.text1);
            CheckBox checkBoxView = rowView.findViewById(R.id.checkBox);

            checkBoxView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
					rowData[position].checked = ((CheckBox) v).isChecked();
                }
            });

			if (textView != null) {
				textView.setText(rowData[position].string);
			}
			checkBoxView.setChecked(rowData[position].checked);
		}
		return rowView;
	}
}
