package com.example.theseus.teleprompter.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by theseus on 2/12/16.
 */

public class ScriptContract {
    public static final String CONTENT_AUTHORITY="com.example.theseus.teleprompter";
    public static final Uri BASE_URI=Uri.parse("content://"+CONTENT_AUTHORITY);


    public static final class ScriptEntry implements BaseColumns{
        public static final String PATH_SCRIPT="scripts";
        public static final String TABLE_NAME="scripts";
        public static final String COLUMN_TITLE="title";
        public static final String COLUMN_CONTENT="content";
        public static final Uri CONTENT_URI=BASE_URI.buildUpon().appendPath(PATH_SCRIPT).build();
        public static final String PATH_SEARCH_KEY="search";

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCRIPT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SCRIPT;
        public static Uri buildUriFromId(long id){
            return ContentUris.withAppendedId(CONTENT_URI,id);
        }

        public static Uri buildUriFromKey(String key){
            return CONTENT_URI.buildUpon().appendPath(PATH_SEARCH_KEY).appendPath(key).build();
        }
        public static String getSearchKeyFromUri(Uri uri){
            return uri.getPathSegments().get(2);
        }
        public static long getIdFromUri(Uri uri){
            return Long.parseLong(uri.getPathSegments().get(1));
        }
    }
}
