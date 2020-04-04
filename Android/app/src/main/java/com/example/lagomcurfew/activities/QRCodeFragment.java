package com.example.lagomcurfew.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        btnBookTimeSlot.setActivated(true);
        btnBookTimeSlot.setOnClickListener(this);

        return retView;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) getActivity();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onClick(View v) {


        switch (retView.getId()){
            case R.id.btn_show_qr_code: {
                //do something
            }
            case R.id.btn_change_booking: {
                //do something
            }

            case R.id.btn_book_time_slot: {

                //switch to time slot fragment to show available time slots
                TimeSlotFragment timeSlotFragment = new TimeSlotFragment();
                mMainActivity.doFragmentTransaction(timeSlotFragment, true);
                break;
            }
        }
    }
}
 /*QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode("QR CODE", BarcodeFormat.QR_CODE, 512, 512);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ((ImageView) retView.findViewById(R.id.QR_code)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
        */