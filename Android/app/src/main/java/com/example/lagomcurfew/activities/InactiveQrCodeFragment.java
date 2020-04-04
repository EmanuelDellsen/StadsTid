package com.example.lagomcurfew.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InactiveQrCodeFragment extends Fragment {

    private View retView;
    private ImageView iQRCode;
    private TextView txtNextBooking;
    private TextView txtPassNonActive;
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
        retView = inflater.inflate(R.layout.fragment_inactive_qr_code, container, false);

        //Change background color to green (active qr code)
        retView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundNonActive));

        //Call to generate qr-code including findViewById.
        setQRCode();
        txtPassNonActive = retView.findViewById(R.id.pass_non_active);
        txtNextBooking = retView.findViewById(R.id.next_booking);

        //if there is a booking, display the date and time
        if(mBooking != null){
            setNextBooking();
        }

        return retView;
    }

    public void setNextBooking(){
        //get swedish locale
        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);

        //format booking into preferred format
        SimpleDateFormat df = new SimpleDateFormat("EEE dd-MMMM-yyyy HH:mm", Locale.getDefault());
        String formattedDate = df.format(mBooking);

        //change text that is displayed
        txtNextBooking.setText("Nästa pass:\n"+formattedDate);
    }

    public void setQRCode(){

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

