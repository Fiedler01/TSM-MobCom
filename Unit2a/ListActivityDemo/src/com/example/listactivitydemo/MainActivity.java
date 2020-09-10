package com.example.listactivitydemo;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
 * displays a list with dummy content
 */
public class MainActivity extends ListActivity {
    List<String> mValues = new ArrayList<>();

    /*
     * put dummy content into mValues (for demo and debugging purposes only)
     */
    protected void loadContent() {
        for (int i = 0; i < 64; i++) {
            mValues.add("Item " + String.valueOf(i + 1));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.arrayadapterdemo.R.layout.main);

        loadContent();

        // Log.i("ListActivityDemo",
        // "MainActivity:onCreate - number of strings in mValues: " +
        // mValues.size());

        // create ArrayAdapter; parameters:
        // context The current context
        // resource TextView for a single row - take a layout form the built-in
        // android.R
        // objects The objects to represent in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mValues);

        // Assign adapter to ListView
        setListAdapter(adapter);
    }

    /*
     * show a Toast with position and item when an item is clicked
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(
                getApplicationContext(),
                "Position :" + position + "  ListItem : "
                        + mValues.get(position), Toast.LENGTH_LONG).show();
    }
}