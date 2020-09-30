package com.example.listactivitydemo;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import static java.lang.String.format;
import static java.util.Locale.getDefault;

/*
 * Implements a very simple list activity for demo purposes
 */
public class MainActivity extends ListActivity {
    private final RowData[] rowData = new RowData[64];

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // initialize the array with some data (for demo and debugging purposes
        // only)
        for (int i = 0; i < rowData.length; i++) {
            rowData[i] = new RowData(format(getDefault(), "Item %d", i + 1), i % 2 == 0);
        }

        // construct and register the adapter
        ListAdapter adapter = new MySimpleExtendedArrayAdapter(this, rowData);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        CharSequence text = format(getDefault(), "Item %d clicked, checked items %d of %d",
                position + 1,
                checkedCount(),
                rowData.length);
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    private int checkedCount() {
        int checkedCount = 0;
        for (RowData row : rowData) {
            checkedCount += row.checked ? 1 : 0;
        }
        return checkedCount;
    }

}