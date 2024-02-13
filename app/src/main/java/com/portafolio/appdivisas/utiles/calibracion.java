package com.portafolio.appdivisas.utiles;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.provider.Settings;
import android.util.DisplayMetrics;

import java.util.Calendar;

public class calibracion {

    private final static String TAG = "calibracion";

    public boolean isOnline(Context context) {
        Activity activity=(Activity) context;
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
           return true;

        } else {

            return false;
        }
    }

    public String Get_UUID(Context context) {
        //Log.d(TAG, "Get_UUID() called with: context = [" + context + "]");
        String UUID = "";
        UUID = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return UUID;
    }

    public void Vibrar(Context context) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(1000);
        }

    }

    public String AnchoDispositivo(Context context) {
        //AppPreferences tamañoTableta = new AppPreferences(context, "tableta");
        DisplayMetrics metricsP = new DisplayMetrics();
        String resultado = "";
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metricsP);
        int width = metricsP.widthPixels; // ancho absoluto en pixels
        int height = metricsP.heightPixels; // alto absoluto en pixels
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dpWidth = (metrics.widthPixels / metrics.density);
        float dpHeight = metrics.heightPixels / metrics.density;
        int dpWidthInt = (int) dpWidth;

        String DPWidth = String.valueOf(dpWidthInt);
        String DPHeight = String.valueOf(dpHeight);

        Log.i(TAG, "Dimensaciones-DPWidth: " + DPWidth);
        Log.i(TAG, "Dimensaciones-DPHeight: " + DPHeight);

        resultado = DPWidth;
        return resultado;
    }

    public String AltoDispositivo(Context context) {
        //AppPreferences tamañoTableta = new AppPreferences(context, "tableta");
        DisplayMetrics metricsP = new DisplayMetrics();
        String resultado = "";
        Activity activity = (Activity) context;
        activity.getWindowManager().getDefaultDisplay().getMetrics(metricsP);
        int width = metricsP.widthPixels; // ancho absoluto en pixels
        int height = metricsP.heightPixels; // alto absoluto en pixels
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dpWidth = (metrics.widthPixels / metrics.density);
        float dpHeight = metrics.heightPixels / metrics.density;
        int dpWidthInt = (int) dpWidth;

        String DPWidth = String.valueOf(dpWidthInt);
        String DPHeight = String.valueOf(dpHeight);

        Log.i(TAG, "Dimensaciones-DPWidth: " + DPWidth);
        Log.i(TAG, "Dimensaciones-DPHeight: " + DPHeight);

        resultado = DPHeight;
        return resultado;
    }
    public int MedirBateria(Context context) {

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float battery = (level / (float) scale) * 100;

        Log.i(TAG, "niveles Bateria: " + battery);
        return (int) battery;
    }

    public boolean GetHorizontal(Context context) {
        boolean horizontal = false;

        Resources resources = context.getResources();
        int orientacion = resources.getConfiguration().orientation;
        if (orientacion == 2) horizontal = true;
        return horizontal;
    }
    public void DensidadPixeles(Context context) {
        Activity activity = (Activity) context;
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        double density = activity.getResources().getDisplayMetrics().density;

        Log.i(TAG, "DensidadPixeles: " + density);

        if (0.75 <= density && density < 1.0) {
            Log.i(TAG, "DensidadPixeles: ldpi");

        }
        if (1.0 <= density && density < 1.5) {
            Log.i(TAG, "DensidadPixeles: mdpi");

        }
        if (1.5 <= density && density < 2.0) {
            Log.i(TAG, "DensidadPixeles: hdpi");

        }
        if (2.0 <= density && density < 3.0) {
            Log.i(TAG, "DensidadPixeles: xhdpi");

        }
        if (3.0 <= density && density <= 4.0) {
            Log.i(TAG, "DensidadPixeles: xxhdpi y xxxhdpi");

        }
    }
    public String GetDensidadPixeles(Context context) {
        Activity activity = (Activity) context;
        String resultado = "";
        DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        double density = activity.getResources().getDisplayMetrics().density;

        Log.i(TAG, "Dimensaciones-DensidadPixeles: " + density);

        if (0.75 <= density && density < 1.0) {
            resultado = "ldpi";
            Log.i(TAG, "Dimensaciones-Ancho: " + AnchoDispositivo(context));
            Log.i(TAG, "Dimensaciones-Alto: " + AltoDispositivo(context));
            Log.i(TAG, "Dimensaciones-DensidadPixeles: ldpi");

        }
        if (1.0 <= density && density < 1.5) {
            resultado = "mdpi";
            Log.i(TAG, "Dimensaciones-Ancho: " + AnchoDispositivo(context));
            Log.i(TAG, "Dimensaciones-Alto: " + AltoDispositivo(context));
            Log.i(TAG, "Dimensaciones-DensidadPixeles: mdpi");

        }
        if (1.5 <= density && density < 2.0) {
            resultado = "hdpi";
            Log.i(TAG, "Dimensaciones-Ancho: " + AnchoDispositivo(context));
            Log.i(TAG, "Dimensaciones-Alto: " + AltoDispositivo(context));
            Log.i(TAG, "Dimensaciones-DensidadPixeles: hdpi");

        }
        if (2.0 <= density && density < 3.0) {
            resultado = "xhdpi";
            Log.i(TAG, "Dimensaciones-Ancho: " + AnchoDispositivo(context));
            Log.i(TAG, "Dimensaciones-Alto: " + AltoDispositivo(context));
            Log.i(TAG, "Dimensaciones-DensidadPixeles: xhdpi");

        }
        if (3.0 <= density && density <= 4.0) {
            resultado = "xxhdpi";
            Log.i(TAG, "Dimensaciones-Ancho: " + AnchoDispositivo(context));
            Log.i(TAG, "Dimensaciones-Alto: " + AltoDispositivo(context));
            Log.i(TAG, "Dimensaciones-DensidadPixeles: xxhdpi y xxxhdpi");

        }
        return resultado;
    }

    public String identificarTipoDispositivo(Context context) {
        Log.d(TAG, "identificarTipoDispositivo() called with: context = [" + context + "]");
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        float screenWidthInches = metrics.widthPixels / metrics.xdpi;
        float screenHeightInches = metrics.heightPixels / metrics.ydpi;
        //Log.d(TAG, "screenWidthInches = [" + screenWidthInches + "]");
        //Log.d(TAG, "screenHeightInches = [" + screenHeightInches + "]");

        float screenDiagonalInches = (float) Math.sqrt(Math.pow(screenWidthInches, 2) + Math.pow(screenHeightInches, 2));

        /*if (screenDiagonalInches >= 7.0) {
               Log.d(TAG, "identificarTipoDispositivo: Tablet screenDiagonalInches "+screenDiagonalInches + "Tablet");
            return "Tablet";
        } else {
               Log.d(TAG, "identificarTipoDispositivo: Teléfono screenDiagonalInches "+screenDiagonalInches + "Teléfono");

            return "Teléfono";
        }*/

        //---------------------------------------------------------------------
        int screenSize = resources.getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        Log.d(TAG, "identificarTipoDispositivo- screenSize:" + screenSize);
        Log.d(TAG, "identificarTipoDispositivo- Configuration.SCREENLAYOUT_SIZE_LARGE:" + Configuration.SCREENLAYOUT_SIZE_LARGE);
        Log.d(TAG, "identificarTipoDispositivo- Configuration.SCREENLAYOUT_SIZE_XLARGE:" + Configuration.SCREENLAYOUT_SIZE_XLARGE);

        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE || screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Log.d(TAG, "identificarTipoDispositivo: Tablet");
            return "Tablet";
        } else {
            Log.d(TAG, "identificarTipoDispositivo: Teléfono screenDiagonalInches " + screenDiagonalInches + "Teléfono");

            return "Teléfono";
        }


    }

    private String getTodaysDate() {

        final Calendar c = Calendar.getInstance();
        int todaysDate = (c.get(Calendar.YEAR) * 10000) + ((c.get(Calendar.MONTH) + 1) * 100) + (c.get(Calendar.DAY_OF_MONTH));
        //Log.w("DATE:", String.valueOf(todaysDate));
        return (String.valueOf(todaysDate));

    }

    private String getCurrentTime() {

        final Calendar c = Calendar.getInstance();
        int currentTime = (c.get(Calendar.HOUR_OF_DAY) * 10000) + (c.get(Calendar.MINUTE) * 100) + (c.get(Calendar.SECOND));
        //Log.w("TIME:", String.valueOf(currentTime));
        return (String.valueOf(currentTime));

    }
}
