package com.example.lagomcurfew.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.lagomcurfew.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Date;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class ActiveQrCodeFragment extends Fragment {

    private View retView;
    private ImageView iQRCode;
    private TextView txtCountDownTimer;
    private TextView txtPassActivated;
    private ImageView iBottomLogo;
    private MainActivity mMainActivity;
    private Date mBooking;

    public void onAttach(Context context) {
        super.onAttach(context);
        mMainActivity = (MainActivity) getActivity();

        //get booking from shared preferences
        mBooking = mMainActivity.getSharedBooking();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retView = inflater.inflate(R.layout.fragment_active_qr_code, container, false);

        //Change background color to green (active qr code)
        retView.setBackgroundColor(ContextCompat.getColor(mMainActivity.getContext(), R.color.backgroundActive));

        //Call to generate qr-code including findViewById.
        setQRCode();
        txtCountDownTimer = retView.findViewById(R.id.count_down_timer);
        txtPassActivated = retView.findViewById(R.id.pass_activated);

        //if there is a booking, start the countdown
        if(mBooking != null){
            //set mBooking to end time (3 hours later) only time
            mBooking = addHoursToDate(mBooking,3);

            //first set the remaining time to change the text upon creation of fragment
            setRemainingTime();

            //start the countdown which calls setRemainingTime every 1 second
            startCountdown();
        }

        return retView;
    }

    private void startCountdown(){

        //https://www.youtube.com/watch?v=6sBqeoioCHE
        Thread t=new Thread(){
            int count = 0;
            @Override
            public void run(){

                while(!isInterrupted()){
                    try {
                        Thread.sleep(1000);  //1000ms = 1 sec
                        mMainActivity.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                setRemainingTime();
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }

    private void setRemainingTime(){
        //get current date and time
        Date current = Calendar.getInstance().getTime();

        //get remaining time
        long remain = getDiffInMilliSeconds(current, mBooking);

        //display as hours and minutes
        @SuppressLint("DefaultLocale") String str = String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(remain),
                TimeUnit.MILLISECONDS.toMinutes(remain) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(remain)),
                TimeUnit.MILLISECONDS.toSeconds(remain) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remain)));

        //change text
        txtCountDownTimer.setText("Time left:\n" + str);
    }

    public Date addHoursToDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    private long getDiffInMilliSeconds(Date date1, Date date2)
    {
        long diff = date2.getTime() - date1.getTime();
        return diff;
    }

    private void setQRCode(){

        QRCodeWriter writer = new QRCodeWriter();
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
            ((ImageView) retView.findViewById(R.id.qr_code)).setImageBitmap(bmp);

        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
