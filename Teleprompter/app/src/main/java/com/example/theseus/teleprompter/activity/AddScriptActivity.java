package com.example.theseus.teleprompter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.theseus.teleprompter.R;
import com.example.theseus.teleprompter.ToastListener;
import com.example.theseus.teleprompter.data.ScriptContract;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class AddScriptActivity extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_script);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent editIntent = getIntent();
        if (editIntent != null && editIntent.hasExtra(ScriptContract.ScriptEntry._ID)) {
            getSupportActionBar().setTitle(getString(R.string.edit_script_title_bar));
        }
        mAdView = (AdView) findViewById(R.id.adView);
        mAdView.setAdListener(new ToastListener(this));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

}
