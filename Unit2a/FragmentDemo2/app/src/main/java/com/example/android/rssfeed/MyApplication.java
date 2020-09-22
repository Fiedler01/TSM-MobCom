package com.example.android.rssfeed;

import android.app.Application;

import com.example.android.rssfeedlibrary.RssEntry;

import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {
    public static List<RssEntry> list = new ArrayList<>();
}
