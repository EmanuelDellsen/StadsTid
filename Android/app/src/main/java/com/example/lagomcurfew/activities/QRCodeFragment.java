package com.example.lagomcurfew.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lagomcurfew.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class QRCodeFragment extends Fragment implements View.OnClickListener {

    private View retView;
    private ImageView iTopLogo;
    private ImageView iGovernmentLogo;
    private ImageButton btnShowQRCode;
    private ImageButton btnBookTimeSlot;
    private ImageButton btnChangeBooking;
    private ImageView iTimeSlotTicket;
    private MainActivity mMainActivity;
    private boolean qrIsActive;
    private TimeSlot mTimeSlot;

    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) getActivity();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retView = inflater.inflate(R.layout.fragment_qr_code, container, false);
        iTopLogo = retView.findViewById(R.id.top_logo);
        iGovernmentLogo = retView.findViewById(R.id.government_logo);
        btnShowQRCode = retView.findViewById(R.id.btn_show_qr_code);
        btnChangeBooking = retView.findViewById(R.id.btn_change_booking);
        btnBookTimeSlot = retView.findViewById(R.id.btn_book_time_slot);
        iTimeSlotTicket = retView.findViewById(R.id.ticket_logo);

        btnShowQRCode.setActivated(true);
        btnShowQRCode.setOnClickListener(this);
        btnChangeBooking.setActivated(true);
        btnChangeBooking.setOnClickListener(this);
        btnBookTimeSlot.setOnClickListener(this);

        return retView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_show_qr_code: {

                //check if qr is active, inflate next fragment depending on status
                if(qrIsActive){
                    ActiveQrCodeFragment activeQrCodeFragment = new ActiveQrCodeFragment();
                    mMainActivity.doFragmentTransaction(activeQrCodeFragment,true);
                } else {
                    InactiveQrCodeFragment inactiveQrCodeFragment = new InactiveQrCodeFragment();
                    mMainActivity.doFragmentTransaction(inactiveQrCodeFragment,true);
                }

                break;
            }
            case R.id.btn_change_booking: {
                //do something

                break;
            }

            case R.id.btn_book_time_slot: {

                //switch to time slot fragment to show available time slots
                TimeSlotFragment timeSlotFragment = new TimeSlotFragment();
                mMainActivity.doFragmentTransaction(timeSlotFragment, true);
                break;
            }
        }
    }

    public boolean getQrStatus(){
        // do something to get the qr status
        return false;
    }
}
