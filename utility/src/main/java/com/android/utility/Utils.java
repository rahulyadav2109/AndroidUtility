package com.android.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;


import java.io.File;

/**
 * Created by pawan on 8/2/17.
 */

public class Utils {

    public static void savePreferenceData(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void savePreferenceData(Context context, String key, Boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void savePreferenceData(Context context, String key, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static void savePreferenceData(Context context, String key, Object value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, new Gson().toJson(value));
        editor.commit();
    }

    public static void clearPreferences(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }

    public static void removePreferenceData(Context context, String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().remove(key).commit();
    }

    public static String readPreferenceData(Context context, String key, String defaultValue) {
        if (context != null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getString(key, defaultValue);
        }
        return null;
    }

    public static Boolean readPreferenceData(Context context, String key, boolean defaultValue) {
        if (context != null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getBoolean(key, defaultValue);
        }
        return false;
    }

    public static int readPreferenceData(Context context, String key, int defaultValue) {
        if (context != null) {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            return sp.getInt(key, defaultValue);
        }
        return -1;
    }

    public static Object readPreferenceData(Context context, String key, String defaultValue, Class clazz) {
        String result = readPreferenceData(context, key, defaultValue);
        if (result != null) {
            return new Gson().fromJson(result, clazz);
        }
        return result;
    }

    public static void createDirectory(Context context, String path) {
        String tempPath = "";
        File dirPath;
        for (String dir : path.split("/")) {
            dirPath = new File(context.getFilesDir(), tempPath + dir);
            if (!dirPath.exists()) {
                dirPath.mkdir();
                tempPath = tempPath + dir + "/";
            } else {
                tempPath = tempPath + dir + "/";
            }
        }
    }

    public static void generateShortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void generateLongToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressBar(ProgressDialog progressDialog) {
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progressDialog.show();
        //progressDialog.setContentView(R.layout.progress_dialog_layout);
        progressDialog.setCancelable(true);
    }

    public static void showProgressBar(String title, String message, ProgressDialog progressDialog) {
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.show();
        progressDialog.setCancelable(true);
    }

    public static void hideProgressBar(ProgressDialog progressDialog) {
        try {
            if (progressDialog != null)
                progressDialog.cancel();
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectory(Context context, String directory) {
        File dir = new File(context.getFilesDir() + directory);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                new File(dir, children[i]).delete();
            }
        }
    }



}