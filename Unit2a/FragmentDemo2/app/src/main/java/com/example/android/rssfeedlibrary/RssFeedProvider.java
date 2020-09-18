package com.example.android.rssfeedlibrary;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssFeedProvider {
    private static final String SUBTITLE = "subtitle";
    private static final String SUMMARY = "description";
    private static final String FEED = "feed";
    private static final String LINK = "link";
    private static final String TITLE = "title";
    private static final String ENTRY = "item";

    public static List<RssEntry> parse(String rssFeed) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        List<RssEntry> list = new ArrayList<>();
        XmlPullParser parser = Xml.newPullParser();
        InputStream stream = null;
        try {
            // auto-detect the encoding from the stream
            stream = new URL(rssFeed).openConnection().getInputStream();
            parser.setInput(stream, null);
            int eventType = parser.getEventType();
            boolean done = false;
            RssEntry item = null;
            while (eventType != XmlPullParser.END_DOCUMENT && !done) {
                String name;
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase(ENTRY)) {
                            Log.i("new item", "Create new item");
                            item = new RssEntry();
                        } else if (item != null) {
                            if (name.equalsIgnoreCase(LINK)) {
                                Log.i("Attribute", "setLink");
                                item.setLink(parser.nextText().trim());
                            } else if (name.equalsIgnoreCase(SUMMARY)) {
                                Log.i("Attribute", "description");
                                item.setSummary(parser.nextText().trim());
                            } else if (name.equalsIgnoreCase(SUBTITLE)) {
                                Log.i("Attribute", "date");
                                item.setSubtitle(parser.nextText());
                            } else if (name.equalsIgnoreCase(TITLE)) {
                                Log.i("Attribute", "title");
                                item.setTitle(parser.nextText().trim());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        Log.i("End tag", name);
                        if (name.equalsIgnoreCase(ENTRY) && item != null) {
                            Log.i("Added", item.toString());
                            list.add(item);
                        } else if (name.equalsIgnoreCase(FEED)) {
                            done = true;
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
