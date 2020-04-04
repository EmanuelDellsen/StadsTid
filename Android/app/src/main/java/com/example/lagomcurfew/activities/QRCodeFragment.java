package com.example.lagomcurfew.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lagomcurfew.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QRCodeFragment extends Fragment implements View.OnClickListener {

    private View retView;
    private ImageView iTopLogo;
    private ImageView iGovernmentLogo;
    private ImageButton btnShowQRCode;
    private ImageButton btnBookTimeSlot;
    private ImageButton btnChangeBooking;
    private ImageView iTimeSlotTicket;
    private MainActivity mMainActivity;
    private TextView iTimeSlotInfo;
    private Date mBooking;

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

        //Get shared booking, null if no booking has been made
        mBooking = mMainActivity.getSharedBooking();

        iTopLogo = retView.findViewById(R.id.top_logo);
        iGovernmentLogo = retView.findViewById(R.id.government_logo);
        btnShowQRCode = retView.findViewById(R.id.btn_show_qr_code);
        btnBookTimeSlot = retView.findViewById(R.id.btn_book_time_slot);
        iTimeSlotTicket = retView.findViewById(R.id.ticket_logo);
        iTimeSlotInfo = retView.findViewById(R.id.booking_info);

        btnShowQRCode.setActivated(true);
        btnShowQRCode.setOnClickListener(this);
        btnBookTimeSlot.setOnClickListener(this);

        //display current booking
        if(mBooking != null){
            setTicketInfo();
        }
        return retView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_show_qr_code: {

                //check booking is active
                if(mMainActivity.isBookingActive()){
                    ActiveQrCodeFragment activeQrCodeFragment = new ActiveQrCodeFragment();
                    mMainActivity.doFragmentTransaction(activeQrCodeFragment,true);
                } else {
                    InactiveQrCodeFragment inactiveQrCodeFragment = new InactiveQrCodeFragment();
                    mMainActivity.doFragmentTransaction(inactiveQrCodeFragment,true);
                }
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

    public void setTicketInfo(){
        //format booking into preferred format
        SimpleDateFormat df = new SimpleDateFormat("dd-MMMM-yyyy");
        String formattedDate = df.format(mBooking);

        //hour format
        SimpleDateFormat hf = new SimpleDateFormat("HH:mm");

        //set start time
        String start_time = hf.format(mBooking);

        //set end time
        Date end_time = addHoursToDate(mBooking,3);
        String formattedHour = hf.format(end_time);

        //change text that is displayed
        iTimeSlotInfo.setText("Next booking:\n"+formattedDate+"\n"+start_time+" "+formattedHour);
    }

    public Date addHoursToDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }
}
