package com.example.petri.dbtestv1;

import static android.provider.BaseColumns._ID;
import static com.example.petri.dbtestv1.DbDefinitions.COL_NAME;
import static com.example.petri.dbtestv1.DbDefinitions.COL_TIME;
import static com.example.petri.dbtestv1.DbDefinitions.TABLE_EVENTS;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class EventDisplayActivity extends Activity {
    private static String[] FROM = { _ID, COL_TIME, COL_NAME, };
    private EventDBHelper eventDBHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        eventDBHelper = new EventDBHelper(this);
        try {
            addEvent();
            Cursor cursor = getEvents();
            showEvents(cursor);
        } finally {
            eventDBHelper.close();
        }
    }

    private void addEvent() {
        // Insert a new record into the Events data source.
        // You would do something similar for delete and update.
        SQLiteDatabase db = eventDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TIME, System.currentTimeMillis());
        values.put(COL_NAME, "Passed by EventDisplayActivity:onCreate");
        db.insertOrThrow(TABLE_EVENTS, null, values);
    }

    private Cursor getEvents() {
        // Perform a managed query. The Android system handles closing
        // and re-querying the cursor when needed.
        SQLiteDatabase db = eventDBHelper.getReadableDatabase();

        String ORDER_BY = COL_TIME + " DESC";
        return db.query(TABLE_EVENTS, FROM, null, null, null, null, ORDER_BY);
    }

    private void showEvents(Cursor cursor) {
        // Stuff them all into a big string
        StringBuilder uiText = new StringBuilder("Events in Table:\n");
        while (cursor.moveToNext()) {
            // Could use getColumnIndexOrThrow() to get indexes
            long id = cursor.getLong(0);
            long time = cursor.getLong(1);
            String name = cursor.getString(2);
            uiText.append(id).append(" | ");
            uiText.append(time).append(" | ");
            uiText.append(name).append("\n");
        }
        // Display on the screen
        TextView textView = findViewById(R.id.text);
        textView.setText(uiText);
    }
}