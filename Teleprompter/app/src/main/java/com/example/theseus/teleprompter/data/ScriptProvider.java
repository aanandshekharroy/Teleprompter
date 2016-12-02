package com.example.theseus.teleprompter.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.media.UnsupportedSchemeException;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

/**
 * Created by theseus on 3/12/16.
 */

public class ScriptProvider extends ContentProvider{
    static final int ALL_SCRIPTS=0;
    static final int SCRIPT_WITH_ID=1;
    static final int SCRIPT_WITH_SEARCH_KEY=2;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private ScriptDBHelper mScriptDBHelper;
    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(ScriptContract.CONTENT_AUTHORITY, ScriptContract.ScriptEntry.PATH_SCRIPT,ALL_SCRIPTS);
        matcher.addURI(ScriptContract.CONTENT_AUTHORITY,ScriptContract.ScriptEntry.PATH_SCRIPT+"/#",SCRIPT_WITH_ID);
        matcher.addURI(ScriptContract.CONTENT_AUTHORITY, ScriptContract.ScriptEntry.PATH_SCRIPT+ "/"+ScriptContract.ScriptEntry.PATH_SEARCH_KEY+"/*",SCRIPT_WITH_SEARCH_KEY);
        return matcher;
    }


    @Override
    public boolean onCreate() {
        mScriptDBHelper=new ScriptDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor = null;
        switch (sUriMatcher.match(uri)){
            case ALL_SCRIPTS:
                retCursor=getAllScripts(uri,projection,sortOrder);
                break;
            case SCRIPT_WITH_ID:
                retCursor=getScriptFromId(uri,projection,sortOrder);
                break;
            case SCRIPT_WITH_SEARCH_KEY:
                retCursor=getScriptsFromSearchKey(uri,projection,sortOrder);
                break;
        }
        retCursor.setNotificationUri(getContext().getContentResolver(),uri);
        return retCursor;
    }

    private Cursor getScriptsFromSearchKey(Uri uri, String[] projection, String sortOrder) {
        String key= ScriptContract.ScriptEntry.getSearchKeyFromUri(uri);
        String selectionWithSearchKey= ScriptContract.ScriptEntry.COLUMN_TITLE+" LIKE ? OR "+ ScriptContract.ScriptEntry.COLUMN_CONTENT+" LIKE ? ";
        String[] selectionArgs=new String[]{"%"+key+"%","%"+key+"%"};
        return mScriptDBHelper.getReadableDatabase().query(ScriptContract.ScriptEntry.TABLE_NAME,projection,selectionWithSearchKey,selectionArgs,null,null,sortOrder);
    }

    private Cursor getScriptFromId(Uri uri, String[] projection, String sortOrder) {
        String selectionWithId="_ID = ?";
        String[] selectionArgs= new String[]{String.valueOf(ScriptContract.ScriptEntry.getIdFromUri(uri))};
        return mScriptDBHelper.getReadableDatabase().query(ScriptContract.ScriptEntry.TABLE_NAME,projection,selectionWithId,selectionArgs,null,null,sortOrder);
    }

    private Cursor getAllScripts(Uri uri, String[] projection, String sortOrder) {
        return mScriptDBHelper.getReadableDatabase().query(ScriptContract.ScriptEntry.TABLE_NAME,projection,null,null,null,null,sortOrder);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matched=sUriMatcher.match(uri);
        switch (matched){
            case ALL_SCRIPTS:
                return ScriptContract.ScriptEntry.CONTENT_TYPE;
            case SCRIPT_WITH_ID:
                return ScriptContract.ScriptEntry.CONTENT_ITEM_TYPE;
            case SCRIPT_WITH_SEARCH_KEY:
                return ScriptContract.ScriptEntry.CONTENT_TYPE;
            default:
                try {
                    throw new UnsupportedSchemeException("Unkown uri: "+uri);
                } catch (UnsupportedSchemeException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
