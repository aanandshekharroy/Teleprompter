package com.example.theseus.teleprompter.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by theseus on 2/12/16.
 */

public class ScriptDBHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "scripts.db";
    private static final int DATABASE_VERSION = 3;

    public ScriptDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_QUERY = "CREATE TABLE " + ScriptContract.ScriptEntry.TABLE_NAME
                + " ( " + ScriptContract.ScriptEntry._ID + " INTEGER PRIMARY KEY, " +
                ScriptContract.ScriptEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                ScriptContract.ScriptEntry.COLUMN_CONTENT + " TEXT NOT NULL );";

        db.execSQL(SQL_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ScriptContract.ScriptEntry.TABLE_NAME);
        onCreate(db);
    }
}
