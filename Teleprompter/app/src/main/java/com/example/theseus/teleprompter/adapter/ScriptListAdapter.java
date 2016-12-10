package com.example.theseus.teleprompter.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.theseus.teleprompter.MainActivityFragment;
import com.example.theseus.teleprompter.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by theseus on 10/12/16.
 */

public class ScriptListAdapter extends RecyclerView.Adapter<ScriptListAdapter.ScriptListAdapterViewHolder> {

    private Cursor mCursor;
    private Context mContext;
    public ScriptListAdapter(Context context){
        mContext=context;
    }
    @Override
    public ScriptListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View list_item_view= LayoutInflater.from(mContext).inflate(R.layout.script_list_item,parent,false);
        return new ScriptListAdapterViewHolder(list_item_view);
    }

    @Override
    public void onBindViewHolder(ScriptListAdapterViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        holder.title.setText(mCursor.getString(MainActivityFragment.COLUMN_TITLE));
    }

    @Override
    public int getItemCount() {
        if(mCursor==null){
            return 0;
        }
        return mCursor.getCount();
    }

    public void swapCursor(Cursor cursor){
        mCursor=cursor;
        notifyDataSetChanged();
    }
    public class ScriptListAdapterViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title)
        TextView title;
        public ScriptListAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

}
