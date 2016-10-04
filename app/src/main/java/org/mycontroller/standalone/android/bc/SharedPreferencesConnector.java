package org.mycontroller.standalone.android.bc;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import org.mycontroller.standalone.android.ApplicationObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Connector class used for save basic data in to shared preferences by key
 */
public class SharedPreferencesConnector {

    public static final String ZIPPY_PREFERENCES = "zippyspot";

    private static SharedPreferencesConnector mInstance;
    private static SharedPreferences mSharedPrefs;

    public static SharedPreferencesConnector getInstance() {
        if (mSharedPrefs == null) {
            mInstance = new SharedPreferencesConnector();
        }
        return mInstance;
    }

    private SharedPreferencesConnector() {
        mSharedPrefs = ApplicationObject.getContext().getSharedPreferences(ZIPPY_PREFERENCES, Context.MODE_PRIVATE);
    }

    public void saveString(String key, String value) {
        Editor editor = mSharedPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void saveInt(String key, int value) {
        Editor editor = mSharedPrefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void saveBoolean(String key, boolean value) {
        Editor editor = mSharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void saveFloat(String key, float value) {
        Editor editor = mSharedPrefs.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void saveLong(String key, long value) {
        Editor editor = mSharedPrefs.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void saveStringList(String key, List<String> value) {
        Editor editor = mSharedPrefs.edit();
        editor.putString(key, TextUtils.join(",", value));
        editor.apply();
    }

    public ArrayList<String> getStringList(String key, String defValue) {
        String temp =  mSharedPrefs.getString(key, defValue);

        List<String> tempList = Arrays.asList(TextUtils.split(temp, ","));
        ArrayList<String> tempArray = new ArrayList<String>();

        for(String objectID: tempList ){

            tempArray.add(objectID);
        }

        return tempArray;
    }


    public String getString(String key, String defValue) {
        return mSharedPrefs.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSharedPrefs.getInt(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPrefs.getBoolean(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSharedPrefs.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSharedPrefs.getLong(key, defValue);
    }

    public void removeValue(String key){
        Editor editor = mSharedPrefs.edit();
        editor.remove(key);
        editor.apply();
    }

    public boolean contains(String key) {
        return mSharedPrefs.contains(key);
    }
}
