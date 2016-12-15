package com.example.theseus.teleprompter.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theseus.teleprompter.AddScriptActivity;
import com.example.theseus.teleprompter.MainActivityFragment;
import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.data.ScriptContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by theseus on 10/12/16.
 */

public class ScriptListAdapter extends RecyclerView.Adapter<ScriptListAdapter.ScriptListAdapterViewHolder> {

    private Cursor mCursor;
    private Context mContext;
//    private String TITLE
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
    public class ScriptListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

//        @BindView(R.id.title)
        TextView title;
        ImageButton imageButton;
        public ScriptListAdapterViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            imageButton=(ImageButton)itemView.findViewById(R.id.edit);
            imageButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if(v.getId()==imageButton.getId()){
                int pos=getAdapterPosition();
                mCursor.moveToPosition(pos);
                Intent addScriptintent=new Intent(mContext,AddScriptActivity.class);
                addScriptintent.putExtra(ScriptContract.ScriptEntry._ID,mCursor.getLong(MainActivityFragment.COLUMN_ID));
                addScriptintent.putExtra(ScriptContract.ScriptEntry.COLUMN_TITLE,mCursor.getString(MainActivityFragment.COLUMN_TITLE));
                addScriptintent.putExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT,mCursor.getString(MainActivityFragment.COLUMN_CONTENT));
                mContext.startActivity(addScriptintent);
            }
        }
    }

}
