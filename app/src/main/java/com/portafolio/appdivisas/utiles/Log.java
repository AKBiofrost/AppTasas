package com.portafolio.appdivisas.utiles;

import com.squareup.picasso.BuildConfig;

public class Log {
    static final boolean LOG = BuildConfig.DEBUG;
    static final String LOGS = BuildConfig.BUILD_TYPE;

    public static void i(String tag, String string) {
        if (LOGS.equalsIgnoreCase("release")==false){
            android.util.Log.i(tag, string);
        }else{
            android.util.Log.i(tag, string);
        }
    }

    public static void e(String tag, String string) {

        if (LOGS.equalsIgnoreCase("release")==false){
            android.util.Log.e(tag, string);
        }
    }

    public static void d(String tag, String string) {
        if (LOGS.equalsIgnoreCase("release")==false) android.util.Log.d(tag, string);
    }

    public static void v(String tag, String string) {
        if (LOGS.equalsIgnoreCase("release")==false) android.util.Log.v(tag, string);
    }

    public static void w(String tag, String string) {
        if (LOGS.equalsIgnoreCase("release")==false) android.util.Log.w(tag, string);
    }
}