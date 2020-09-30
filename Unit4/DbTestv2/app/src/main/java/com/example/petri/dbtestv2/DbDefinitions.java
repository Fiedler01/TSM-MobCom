package com.example.petri.dbtestv2;

import android.provider.BaseColumns;

public interface DbDefinitions extends BaseColumns {
    String TABLE_EVENTS = "events";

    // Columns in the Events database
    String COL_TIME = "time";
    String COL_NAME = "name";
}