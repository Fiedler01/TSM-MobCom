package com.example.android.rssfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.rssfeedlibrary.RssEntry;

import java.util.List;

public class MyAdapter extends ArrayAdapter<RssEntry> {

    private Context context;
    private List<RssEntry> items;

    MyAdapter(Context context, int textViewResourceId,
              List<RssEntry> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(items.get(position).getTitle());
        return view;
    }
}
