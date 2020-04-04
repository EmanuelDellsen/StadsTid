package com.example.lagomcurfew.activities;

import android.content.Context;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.lagomcurfew.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements InterfaceMainActivity {

    private FrameLayout mFrameLayout;

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mFrameLayout = (FrameLayout) findViewById(R.id.frameLayout);

        init();

    }

    private void init() {

        //TimeSlotFragment timeSlotFragment = new TimeSlotFragment();
        //doFragmentTransaction(timeSlotFragment, true);
        QRCodeFragment qrCodeFragment = new QRCodeFragment();
        doFragmentTransaction(qrCodeFragment, true);
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

}
