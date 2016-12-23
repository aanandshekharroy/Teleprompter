package com.example.theseus.teleprompter.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theseus.teleprompter.activity.DetailActivity;
import com.example.theseus.teleprompter.data.ScriptContract;
import com.example.theseus.teleprompter.fragment.MainActivityFragment;
import com.example.theseus.teleprompter.R;

import static com.example.theseus.teleprompter.fragment.MainActivityFragment.COLUMN_CONTENT;
import static com.example.theseus.teleprompter.fragment.MainActivityFragment.COLUMN_TITLE;

/**
 * Created by theseus on 13/12/16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public SearchAdapter(Context context) {
        mContext = context;
    }

    public void swapCursor(Cursor data) {
        mCursor = data;
        notifyDataSetChanged();
    }

    @Override
    public SearchAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.script_search_item, parent, false);

        return new SearchAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapterViewHolder holder, int position) {
        if (mCursor != null && !mCursor.isClosed()) {
            mCursor.moveToPosition(position);
            holder.mTitleTextView.setText(mCursor.getString(COLUMN_TITLE));
            holder.mContentView.setText(mCursor.getString(COLUMN_CONTENT));
        }

    }

    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public class SearchAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView mTitleTextView;
        final TextView mContentView;

        public SearchAdapterViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mContentView = (TextView) itemView.findViewById(R.id.content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            mCursor.moveToPosition(pos);
            Intent detailActivity = new Intent(mContext, DetailActivity.class);
            detailActivity.putExtra(ScriptContract.ScriptEntry.COLUMN_TITLE, mCursor.getString(COLUMN_TITLE));
            detailActivity.putExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT, mCursor.getString(COLUMN_CONTENT));
            mContext.startActivity(detailActivity);
        }
    }
}
