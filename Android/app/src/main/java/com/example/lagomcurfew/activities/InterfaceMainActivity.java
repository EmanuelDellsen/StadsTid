package com.example.lagomcurfew.activities;

import android.content.Context;

import java.util.Date;

public interface InterfaceMainActivity {

    Context getContext();

    public void saveSharedBooking(Date mDate);

    public Date getSharedBooking();


}
