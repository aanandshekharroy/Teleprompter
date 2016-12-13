package com.example.theseus.teleprompter.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

/**
 * Created by theseus on 13/12/16.
 */

public class SearchAdapter {
    private SearchAdapter mSearchAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext;
    public SearchAdapter(Context context) {
        mContext=context;
    }

    public void swapCursor(Cursor data) {
    }
}
