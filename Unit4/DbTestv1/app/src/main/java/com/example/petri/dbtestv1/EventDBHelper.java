package com.example.petri.dbtestv1;

import static android.provider.BaseColumns._ID;
import static com.example.petri.dbtestv1.DbDefinitions.COL_NAME;
import static com.example.petri.dbtestv1.DbDefinitions.COL_TIME;
import static com.example.petri.dbtestv1.DbDefinitions.TABLE_EVENTS;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 3;

    /** Create a helper object for the Events database */
    EventDBHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_EVENTS + " (" + _ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TIME
                + " INTEGER," + COL_NAME + " TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }
}