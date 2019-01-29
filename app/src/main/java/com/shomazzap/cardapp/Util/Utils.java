package com.shomazzap.cardapp.Util;

import android.util.Log;
import android.view.View;

import com.shomazzap.cardapp.BuildConfig;

import androidx.annotation.Nullable;

public class Utils {

    public static void setVisible(@Nullable View view, boolean isVisible) {
        int visibility = isVisible ? View.VISIBLE : View.GONE;
        if (view != null) {
            log(view.toString() + " set " + visibility);
            view.setVisibility(visibility);
        }
    }

    public static void log (String message){
        Log.d("LOG", message);
    }

    public static boolean isDebug (){
        return BuildConfig.DEBUG;
    }

}