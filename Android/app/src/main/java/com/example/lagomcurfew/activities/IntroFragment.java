package com.example.lagomcurfew.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.lagomcurfew.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IntroFragment extends Fragment implements View.OnClickListener {

    private InterfaceMainActivity mInterfaceMainActivity;
    private ImageButton btnBankId;
    private ImageButton btnQRScanner;
    private ImageButton btnVideoTutorial;
    private View retView;
    private MainActivity mMainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        retView = inflater.inflate(R.layout.fragment_intro, container, false);

        btnBankId = retView.findViewById(R.id.btn_bankid);
        btnBankId.setActivated(true);
        btnBankId.setOnClickListener(this);

        return retView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterfaceMainActivity = (InterfaceMainActivity) getActivity();
        mMainActivity = (MainActivity) getActivity();

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_bankid: {
                QRCodeFragment qrCodeFragment = new QRCodeFragment();
                mMainActivity.doFragmentTransaction(qrCodeFragment, false);
            }
        }
    }
}
