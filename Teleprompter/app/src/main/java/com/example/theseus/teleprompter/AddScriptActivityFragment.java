package com.example.theseus.teleprompter;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.theseus.teleprompter.data.ScriptContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_add_script, container, false);
        ButterKnife.bind(this,view);
        return view;
    }
    @OnClick (R.id.save_button)
    public void saveScript(){
        String title= String.valueOf(editTitle.getText());
        String content= String.valueOf(addContent.getText());
        ContentValues values=new ContentValues();
        values.put(ScriptContract.ScriptEntry.COLUMN_TITLE,title);
        values.put(ScriptContract.ScriptEntry.COLUMN_CONTENT,title);
        Uri insertUri=getContext().getContentResolver().insert(ScriptContract.ScriptEntry.CONTENT_URI,values);
        Log.d(LOG_TAG,"inserted uri: "+insertUri);
//        Toast.makeText(getContext(),title,Toast.LENGTH_SHORT).show();

    }

}
