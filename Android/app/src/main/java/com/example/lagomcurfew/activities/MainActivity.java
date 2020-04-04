package com.example.lagomcurfew.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.FrameLayout;

import com.example.lagomcurfew.R;
import com.google.gson.Gson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.sql.Date;
import java.sql.Time;

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

        init();

    }

    private void init() {


        IntroFragment introFragment = new IntroFragment();
        doFragmentTransaction(introFragment, true);
    }

    public void doFragmentTransaction(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frameLayout, fragment);

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

}
