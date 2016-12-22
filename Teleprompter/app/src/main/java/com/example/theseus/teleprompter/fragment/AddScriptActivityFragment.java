package com.example.theseus.teleprompter.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theseus.teleprompter.MyApplication;
import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.data.ScriptContract;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.theseus.teleprompter.fragment.MainActivityFragment.ACTION_DATA_UPDATED;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddScriptActivityFragment extends Fragment {

    private String LOG_TAG=AddScriptActivityFragment.class.getSimpleName();
    @BindView(R.id.add_title)
    EditText editTitle;
    @BindView(R.id.add_content)
    EditText addContent;
    @BindView(R.id.save_button)
    Button saveButton;
    public AddScriptActivityFragment() {
    }
    private int editMode=0;
    private long _id=-1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        activities.add(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_script, container, false);
        ButterKnife.bind(this,view);
        Intent editScript=getActivity().getIntent();
        if(editScript!=null){
//            getActivity().

            editMode=1;
            if(editScript.hasExtra(ScriptContract.ScriptEntry._ID)){
                _id=editScript.getLongExtra(ScriptContract.ScriptEntry._ID,-1);
            }
            if(editScript.hasExtra(ScriptContract.ScriptEntry.COLUMN_TITLE)){
                editTitle.setText(editScript.getStringExtra(ScriptContract.ScriptEntry.COLUMN_TITLE));
            }
            if(editScript.hasExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT)){
                addContent.setText(editScript.getStringExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT));
            }
        }
        return view;
    }
    public void updateWidget(){
        Context context=getContext();
        Intent dataUpdatedNotify=new Intent(ACTION_DATA_UPDATED).setPackage(context.getPackageName());
        context.sendBroadcast(dataUpdatedNotify);
    }
    @OnClick (R.id.save_button)
    public void saveScript(){
        if(editTitle.getText().toString().length()<1||addContent.getText().toString().length()<1){
            Toast.makeText(getContext(),getString(R.string.add_text_warning),Toast.LENGTH_SHORT).show();
            return;
        }
        String title= String.valueOf(editTitle.getText());
        String content= String.valueOf(addContent.getText());
        ContentValues values=new ContentValues();
        values.put(ScriptContract.ScriptEntry.COLUMN_TITLE,title);
        values.put(ScriptContract.ScriptEntry.COLUMN_CONTENT,content);

        if(editMode==1&&_id!=-1){
            int update=getContext().getContentResolver().update(ScriptContract.ScriptEntry.buildUriFromId(_id),values,null,null);
            Log.d(LOG_TAG,"updated:"+update);
        }else{
            Uri insertUri=getContext().getContentResolver().insert(ScriptContract.ScriptEntry.CONTENT_URI,values);
            Tracker tracker=((MyApplication)getActivity().getApplication()).getTracker();
            tracker.send(new HitBuilders.EventBuilder()
            .setCategory("Add script")
            .setAction("Script added")
            .setLabel("Added script")
            .build());
        }



//        Log.d(LOG_TAG,"inserted uri: "+insertUri);
//        Toast.makeText(getContext(),title,Toast.LENGTH_SHORT).show();
        Log.d(LOG_TAG,"intent sent");
        updateWidget();
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
