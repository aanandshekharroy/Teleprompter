package com.example.theseus.teleprompter.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theseus.teleprompter.fragment.MainActivityFragment;
import com.example.theseus.teleprompter.R;

/**
 * Created by theseus on 13/12/16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    public SearchAdapter(Context context) {
        mContext=context;
    }

    public void swapCursor(Cursor data) {
        mCursor=data;
//        notifyDataSetChanged();
    }

    @Override
    public SearchAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.script_search_item,parent,false);

        return new SearchAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapterViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.mTitleTextView.setText(mCursor.getString(MainActivityFragment.COLUMN_TITLE));
        holder.mContentView.setText(mCursor.getString(MainActivityFragment.COLUMN_CONTENT));
    }

    @Override
    public int getItemCount() {
        if(mCursor==null){
            return 0;
        }
        return mCursor.getCount();
    }

    public class SearchAdapterViewHolder extends RecyclerView.ViewHolder{
        final TextView mTitleTextView;
        final TextView mContentView;
        public SearchAdapterViewHolder(View itemView) {
            super(itemView);
            mTitleTextView =(TextView) itemView.findViewById(R.id.title);
            mContentView=(TextView)itemView.findViewById(R.id.content);
        }
    }
}
