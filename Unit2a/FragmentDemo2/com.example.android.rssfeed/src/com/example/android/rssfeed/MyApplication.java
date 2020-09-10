package com.example.android.rssfeed;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

import com.example.android.rssfeedlibrary.RssEntry;

public class MyApplication extends Application {
    public static List<RssEntry> list = new ArrayList<>();
}
