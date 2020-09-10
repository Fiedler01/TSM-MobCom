package com.example.android.rssfeed;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class DetailFragment extends Fragment {

    private WebView webview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        webview = new WebView(getActivity());
        return webview;
    }

    public void show(String url) {
        webview.setInitialScale(50);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(url);

    }
}