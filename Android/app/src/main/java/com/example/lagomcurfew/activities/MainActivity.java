package com.example.lagomcurfew.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import com.example.lagomcurfew.R;
import com.google.gson.Gson;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements InterfaceMainActivity {

    private FrameLayout mFrameLayout;
    private SharedPreferences mPreferences;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        //Shared preferences (https://stackoverflow.com/questions/7145606/how-android-sharedpreferences-save-store-object)
        mPreferences = getPreferences(MODE_PRIVATE);

        //will reset shared preferences if booking has expired
        resetSharedPreferences();

        init();
    }

    private void init() {
        IntroFragment introFragment = new IntroFragment();
        doFragmentTransaction(introFragment, false);
    }

    public void doFragmentTransaction(Fragment fragment, boolean addToBackStack) {
        //get current booking
        Date mBooking = getSharedBooking();

        //check if there is a booking
        if(mBooking != null){
            //will reset shared preferences if booking has expired
            resetSharedPreferences();

        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragment);

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getTag());
        }
        fragmentTransaction.commit();
    }

    public void onBackPressed() {

        if(mFrameLayout != null){
            mFrameLayout.removeAllViews();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(fragmentManager.getBackStackEntryCount() > 1 ) {
            fragmentManager.popBackStack();
            return;
        }
        super.onBackPressed();
    }

    public Context getContext() {
        return this;
    }

    public void saveSharedBooking(Date mDate) {
        SharedPreferences.Editor prefsEditor = mPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mDate);
        prefsEditor.putString("myBooking",json);
        prefsEditor.commit();
    }

    public Date getSharedBooking(){
        Gson gson = new Gson();
        String json = mPreferences.getString("myBooking", "");
        Date mDate = gson.fromJson(json, Date.class);
        return mDate;
    }

    public boolean isBookingActive(){
        //get current date & time
        Date current = Calendar.getInstance().getTime();

        //get current booking
        Date mBooking = getSharedBooking();

        //check if there is a booking
        if(mBooking != null){
            return current.after(mBooking) && current.before(addHoursToDate(mBooking,3));
        } else {
            return false;
        }
    }

    public boolean isBookingExpired(){
        //get current date & time
        Date current = Calendar.getInstance().getTime();

        //get current booking
        Date mBooking = getSharedBooking();

        //check if current time is after end time of booking
        if(current.after(addHoursToDate(mBooking,3))){
            return true;
        } else {
            return false;
        }
    }

    public void resetSharedPreferences(){
        //reset sharedPreferences if booking is expired

        if(isBookingExpired()){
            mPreferences.edit().clear().commit();
        }
    }

    public Date addHoursToDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

}
