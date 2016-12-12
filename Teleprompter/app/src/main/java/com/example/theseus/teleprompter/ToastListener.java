package com.example.theseus.teleprompter;

import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

/**
 * Created by theseus on 11/12/16.
 */

public class ToastListener extends AdListener {
    private String mError="";
    private Context mContext;
    public ToastListener(Context context){
        this.mContext=context;
    }
    @Override
    public void onAdFailedToLoad(int errorCode) {
        switch (errorCode){
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                mError="Internal Error";
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                mError="Network Error";
                break;
        }
        Toast.makeText(mContext,mError,Toast.LENGTH_SHORT).show();
//        super.onAdFailedToLoad(i);
    }
}
