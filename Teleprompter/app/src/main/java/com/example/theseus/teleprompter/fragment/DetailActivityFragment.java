package com.example.theseus.teleprompter.fragment;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.data.ScriptContract;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.attr.textSize;
import static android.os.Build.VERSION_CODES.M;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    private String LOG_TAG=DetailActivityFragment.class.getSimpleName();
    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.button_play)
    ImageButton button_play;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.seekbar_speed)
    SeekBar seekbar_speed;
    @BindView(R.id.seekbar_text_size)
    SeekBar seekbar_text_size;
    @BindView(R.id.seekbar_line_height)
    SeekBar seekbar_line_height;
    @BindView(R.id.linear_layouts_tool_bar)
    LinearLayout linearLayoutToolBar;
    @BindView(R.id.image_button_setting)
    ImageButton imageButtonSetting;
    @BindView(R.id.linear_layout_seekbar_container)
    LinearLayout linearLayoutSeekbarContainer;
    @BindView(R.id.linear_layouts_tools)
    LinearLayout linearLayoutTools;
    long time=1000000000;
    int mPlayMode=0;
    int mScrollBy=1;
    public DetailActivityFragment() {
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @OnClick(R.id.content)
    public void onClickcontent(){
        if(linearLayoutTools.getVisibility()==View.VISIBLE){
            linearLayoutTools.setVisibility(View.GONE);
        }else{
            linearLayoutTools.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        Intent detailIntent=getActivity().getIntent();
        ButterKnife.bind(this,view);
        initializeSeekBars(view);
        if(detailIntent!=null){
            content.setText(detailIntent.getStringExtra(ScriptContract.ScriptEntry.COLUMN_CONTENT) );
        }

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        timer.cancel();
    }

    private void initializeSeekBars(View view) {

        SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
        int seek_bar_text_size_progress=prefs.getInt(getString(R.string.pref_seekbar_text_size_progress),0);
        int base_textSize=Integer.parseInt(getString(R.string.text_size_default));
        content.setTextSize(base_textSize+(seek_bar_text_size_progress*seek_bar_text_size_progress));
        seekbar_text_size.setProgress(seek_bar_text_size_progress);
        seekbar_text_size.setMax(10);
        seekbar_text_size.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int base_text_size=Integer.parseInt(getString(R.string.text_size_default));
                content.setTextSize(base_text_size+progress*progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editValue=pref.edit();
                editValue.putInt(getString(R.string.pref_seekbar_text_size_progress),seekBar.getProgress());
                editValue.apply();
            }
        });



        int seek_bar_speed_progress=prefs.getInt(getString(R.string.pref_seekbar_speed_progress),0);
        int base_speed=Integer.parseInt(getString(R.string.speed_default));
        mScrollBy=base_speed+(seek_bar_speed_progress);
        seekbar_speed.setProgress(seek_bar_speed_progress);
        seekbar_speed.setMax(10);
        seekbar_speed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if(mPlayMode==0){
                    int base_speed=Integer.parseInt(getString(R.string.speed_default));
                    mScrollBy=base_speed+progress;
//                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editValue=pref.edit();
                editValue.putInt(getString(R.string.pref_seekbar_speed_progress),seekBar.getProgress());
                editValue.apply();
            }
        });

        int seek_bar_lineheight_progress=prefs.getInt(getString(R.string.pref_seekbar_lineheight_progress),0);
        int base_lineheight=Integer.parseInt(getString(R.string.line_height_default));
        content.setLineSpacing((float) (seek_bar_lineheight_progress*seek_bar_lineheight_progress),base_lineheight);
        float line_height= (float) (base_lineheight+(float)(0.5*seek_bar_lineheight_progress));
        seekbar_line_height.setProgress(seek_bar_lineheight_progress);
        seekbar_line_height.setMax(10);
        seekbar_line_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                content.setLineSpacing((float) (progress*progress),1);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editValue=pref.edit();
                editValue.putInt(getString(R.string.pref_seekbar_lineheight_progress),seekBar.getProgress());
                editValue.apply();
            }
        });



    }

    ScrollTimer timer=new ScrollTimer(time,15);
    @OnClick(R.id.button_play)
    public void auto_scroll(){
        if(mPlayMode==0){
            mPlayMode=1;
            timer.start();
            button_play.setImageResource(R.drawable.pause24);
//            button_play.setBackgroundColor(R.color.colorPrimaryDark);
        }else{
            mPlayMode=0;
            button_play.setImageResource(R.drawable.play24);
            timer.cancel();
        }
    }

    @OnClick(R.id.image_button_setting)
    public void onClick(){
        if(linearLayoutSeekbarContainer.getVisibility()==View.GONE){
            linearLayoutSeekbarContainer.setVisibility(View.VISIBLE);
        }else if(linearLayoutSeekbarContainer.getVisibility()==View.VISIBLE){
            linearLayoutSeekbarContainer.setVisibility(View.GONE);
        }
    }
    public class ScrollTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public ScrollTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
        mScrollView.smoothScrollBy(0, mScrollBy);
        }

        @Override
        public void onFinish() {

        }

    }

}
