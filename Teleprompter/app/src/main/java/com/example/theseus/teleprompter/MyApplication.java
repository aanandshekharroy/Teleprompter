package com.example.theseus.teleprompter;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import java.util.logging.Level;

/**
 * Created by theseus on 18/12/16.
 */

public class MyApplication extends Application {
    public Tracker mTracker;
    public void startTracking(){
        if(mTracker==null){
            GoogleAnalytics ga=GoogleAnalytics.getInstance(this);
            mTracker=ga.newTracker(R.xml.track_app);
            ga.enableAutoActivityReports(this);
            ga.getLogger().setLogLevel(Logger.LogLevel.VERBOSE);
        }
    }
    public Tracker getTracker(){
        startTracking();
        return mTracker;
    }
}
