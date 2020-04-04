/*
package com.example.lagomcurfew.activities;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {


    private static final String PREF_NAME = "StadsTid";
    private static final String TIME_SLOT = "timeSlot";
    private static final String BOOKED_SLOT = "booked_slot";


    private static SharedPreferencesManager sInstance;
    private final SharedPreferences mSharedPreferences;

    private SharedPreferencesManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedPreferencesManager(context);
        }
    }

    public static synchronized SharedPreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(SharedPreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        mSharedPreferences.edit()
                .put(TIME_SLOT, timeSlot.getDate())
                .apply();
    }

    public String getToken() {
        return mSharedPreferences.getString(TIME_SLOT, "");
    }

    public boolean getTimeSlot() {
        return mSharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }



    public void remove(String key) {
        mSharedPreferences.edit()
                .remove(key)
                .apply();
    }

    public boolean clear() {
        return mSharedPreferences.edit()
                .clear()
                .commit();
    }

    public static String getPrefName() {
        return PREF_NAME;
    }


}
*/
