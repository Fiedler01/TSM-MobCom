package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.android.rssfeedlibrary.RssFeedProvider;
import com.example.android.rssfeedlibrary.RssEntry;

public class MyListFragment extends ListFragment {
    ParseTask parseTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<RssEntry> list = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(getActivity(),
                android.R.layout.simple_list_item_1, list);
        setListAdapter(adapter);
        setRetainInstance(true);
        updateListContent();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        RssEntry item = (RssEntry) getListAdapter().getItem(position);
        updateDetail(item);
    }

    private static class ParseTask extends
            AsyncTask<String, Void, List<RssEntry>> {
        private MyListFragment fragment;

        void setFragment(MyListFragment fragment) {
            this.fragment = fragment;
        }

        @Override
        protected List<RssEntry> doInBackground(String... params) {
            return RssFeedProvider.parse(params[0]);
        }

        @Override
        protected void onPostExecute(List<RssEntry> result) {
            fragment.setListContent(result);
        }
    }

    public void updateListContent() {
        if (parseTask == null) {
            parseTask = new ParseTask();
            parseTask.setFragment(this);
            parseTask.execute("https://www.nzz.ch/recent.rss");
        }
    }

    public void setListContent(List<RssEntry> result) {
        @SuppressWarnings("unchecked")
        ArrayAdapter<RssEntry> listAdapter = (ArrayAdapter<RssEntry>) getListAdapter();
        listAdapter.clear();
        listAdapter.addAll(result);
        parseTask.setFragment(null);
        parseTask = null;
    }

    public interface OnItemSelectedListener {
        void onRssItemSelected(String link);
    }

    private OnItemSelectedListener getListener() {
        if (getActivity() instanceof OnItemSelectedListener) {
            return (OnItemSelectedListener) getActivity();
        } else {
            throw new ClassCastException(getActivity().toString()
                    + " must implemenet MyListFragment.OnItemSelectedListener");
        }
    }

    public void updateDetail(RssEntry item) {
        getListener().onRssItemSelected(item.getLink());
    }
}