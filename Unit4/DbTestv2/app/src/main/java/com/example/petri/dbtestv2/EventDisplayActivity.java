package com.example.petri.dbtestv2;

import static android.provider.BaseColumns._ID;
import static com.example.petri.dbtestv2.DbDefinitions.COL_NAME;
import static com.example.petri.dbtestv2.DbDefinitions.COL_TIME;
import static com.example.petri.dbtestv2.DbDefinitions.TABLE_EVENTS;
import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class EventDisplayActivity extends ListActivity {
    private static String[] FROM = { _ID, COL_TIME, COL_NAME, };
    private static int[] TO = { R.id.rowid, R.id.time, R.id.name, };
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
        // Employ an adapter to set up data binding among layout and cursor
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item, cursor, FROM, TO,0);
        setListAdapter(adapter);
    }
}