package com.example.theseus.teleprompter.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theseus.teleprompter.activity.AddScriptActivity;
import com.example.theseus.teleprompter.fragment.MainActivityFragment;
import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.data.ScriptContract;

/**
 * Created by theseus on 10/12/16.
 */

public class ScriptListAdapter extends RecyclerView.Adapter<ScriptListAdapter.ScriptListAdapterViewHolder> {
    private static final String LOG_TAG=ScriptListAdapter.class.getSimpleName();
    private Cursor mCursor;
    private Context mContext;
    final private ScriptListAdapterOnClickHandler mScriptListAdapterOnClickHandler ;
    final private View mEmptyView;
    public ScriptListAdapter(Context context,ScriptListAdapterOnClickHandler ln,View emptyView){
        mContext=context;
        mScriptListAdapterOnClickHandler=ln;
        mEmptyView=emptyView;
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
        holder.content.setText(mCursor.getString(MainActivityFragment.COLUMN_CONTENT));
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
        if(mCursor!=null&&mCursor.getCount()==0){
            mEmptyView.setVisibility(View.VISIBLE);
        }else if(mEmptyView.getVisibility()==View.VISIBLE){
            mEmptyView.setVisibility(View.GONE);
        }
    }
    public class ScriptListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

//        @BindView(R.id.title)
        TextView title;
        TextView content;
        ImageButton editButton;
        ImageButton deleteButton;
        LinearLayout listItemOptions;
        public ScriptListAdapterViewHolder(View itemView) {
            super(itemView);
            listItemOptions=(LinearLayout)itemView.findViewById(R.id.list_item_options);
            listItemOptions.setVisibility(View.GONE);
            title=(TextView)itemView.findViewById(R.id.title);
            content=(TextView)itemView.findViewById(R.id.content);
            editButton=(ImageButton)itemView.findViewById(R.id.image_button_edit);
            editButton.setOnClickListener(this);
            deleteButton=(ImageButton)itemView.findViewById(R.id.image_button_delete);
            deleteButton.setOnClickListener(this);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void removeAt(int position) {
//            mCursor.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mCursor.getCount());
        }
        @Override
        public void onClick(View v) {
            LinearLayout linearLayoutTextContainer=(LinearLayout)v.findViewById(R.id.list_item_text_container);
            LinearLayout listItemOptions=(LinearLayout)v.findViewById(R.id.list_item_options);
            if(listItemOptions!=null&&listItemOptions.getVisibility()==View.VISIBLE){
              listItemOptions.setVisibility(View.INVISIBLE);
                linearLayoutTextContainer.setVisibility(View.VISIBLE);
                return;
            }
//            listItemOptions.setVisibility(View.VISIBLE);
            Log.d(LOG_TAG,"onClick");
            if(v.getId()==editButton.getId()){
                int pos=getAdapterPosition();
                mCursor.moveToPosition(pos);
                Intent addScriptintent=new Intent(mContext,AddScriptActivity.class);
                addScriptintent.putExtra(ScriptContract.ScriptEntry._ID,mCursor.getLong(MainActivityFragment.COLUMN_ID));
                addScriptintent.putExtra(ScriptContract.ScriptEntry.COLUMN_TITLE,mCursor.getString(MainActivityFragment.COLUMN_TITLE));
                addScriptintent.putExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT,mCursor.getString(MainActivityFragment.COLUMN_CONTENT));
                mContext.startActivity(addScriptintent);
            }else if(v.getId()==deleteButton.getId()){
//                Log.d(LOG_TAG)
                int pos=getAdapterPosition();
                mCursor.moveToPosition(pos);
                Uri deleteUri= ScriptContract.ScriptEntry.buildUriFromId(mCursor.getInt(MainActivityFragment.COLUMN_ID));
                long id=mContext.getContentResolver().delete(deleteUri,null,null);
                Log.d(LOG_TAG,"deleted: id: "+id);
                removeAt(pos);
//                notifyDataSetChanged();
            }else{
                mScriptListAdapterOnClickHandler.onClick(this);
            }
        }

        @Override
        public boolean onLongClick(View v) {
//            Toast.makeText()
            Log.d(LOG_TAG,"onLongClick");
            LinearLayout linearLayoutTextContainer=(LinearLayout)v.findViewById(R.id.list_item_text_container);
            linearLayoutTextContainer.setVisibility(View.INVISIBLE);
            LinearLayout listItemOptions=(LinearLayout)v.findViewById(R.id.list_item_options);
            listItemOptions.setVisibility(View.VISIBLE);
            return true;
        }
    }
    public static interface ScriptListAdapterOnClickHandler{
        void onClick(ScriptListAdapterViewHolder vh);
    }

}
