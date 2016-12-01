package com.example.theseus.teleprompter.data;

import android.provider.BaseColumns;

/**
 * Created by theseus on 2/12/16.
 */

public class ScriptContract {
    public static final class ScriptEntry implements BaseColumns{
        public static final String TABLE_NAME="scripts";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_CONTENT="content";
    }
}
