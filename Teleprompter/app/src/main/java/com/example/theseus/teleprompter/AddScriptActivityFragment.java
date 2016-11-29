package com.example.theseus.teleprompter;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddScriptActivityFragment extends Fragment {

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
//        String content=addContent.getText();
        Toast.makeText(getContext(),title,Toast.LENGTH_SHORT).show();

    }

}
