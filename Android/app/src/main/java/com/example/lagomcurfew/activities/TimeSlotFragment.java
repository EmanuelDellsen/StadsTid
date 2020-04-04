package com.example.lagomcurfew.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lagomcurfew.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class TimeSlotFragment extends Fragment implements View.OnClickListener, TimeSlotAdapter.onItemSelectedListener {

    private InterfaceMainActivity mInterfaceMainActivity;
    private ViewPager viewPager;
    private TimeSlotAdapter timeSlotAdapter;
    private MainActivity mMainActivity;
    private ArrayList<TimeSlot> timeSlots;
    private View retView;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         retView = inflater.inflate(R.layout.fragment_start, container, false);
         initViewPager();
         getDates();
        return retView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterfaceMainActivity = (InterfaceMainActivity) getActivity();

    }

    @Override
    public void onClick(View v) {

    }



    private void initViewPager() {
        timeSlotAdapter = new TimeSlotAdapter( timeSlots, this);
        timeSlotAdapter.setCurrentItem(0);


        viewPager =  retView.findViewById(R.id.viewPager);
        viewPager.setAdapter(timeSlotAdapter);

        // set intial position.
        onPagerItemClick(viewPager.getChildAt(0), 0);
    }

    public void onPagerItemClick(View v, Integer tag) {
        System.out.println(v + " View clicked ");

    }

    private ArrayList<TimeSlot> getDates() {
        timeSlots = new ArrayList<TimeSlot>();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        for (int index = 1; index < 365; index++) {

            TimeSlot date = new TimeSlot();
            date.setDate("" + calendar.get(Calendar.DATE));
            date.setDay(getDay(calendar.get(Calendar.DAY_OF_WEEK)));
            date.setYear("" + calendar.get(Calendar.YEAR));
            date.setMonth("" + getMonth(calendar.get(Calendar.MONTH)));

            date.setFormattedDate(calendar.get(Calendar.YEAR) + "-"
                    + (calendar.get(Calendar.MONTH) + 1) + "-"
                    + calendar.get(Calendar.DATE));

            timeSlots.add(date);

            calendar.add(Calendar.DATE, 1);

        }
        return timeSlots;
    }

    private String getDay(int index) {
        switch (index) {
            case Calendar.SUNDAY:
                return "SUN";
            case Calendar.MONDAY:
                return "MON";
            case Calendar.TUESDAY:
                return "TUE";
            case Calendar.WEDNESDAY:
                return "WED";
            case Calendar.THURSDAY:
                return "THUR";
            case Calendar.FRIDAY:
                return "FRI";
            case Calendar.SATURDAY:
                return "SAT";
        }
        return "";
    }

    private String getMonth(int index) {
        switch (index) {
            case Calendar.JANUARY:
                return "JANUARY";
            case Calendar.FEBRUARY:
                return "FEBRUARY";
            case Calendar.MARCH:
                return "MARCH";
            case Calendar.APRIL:
                return "APRIL";
            case Calendar.MAY:
                return "MAY";
            case Calendar.JUNE:
                return "JUNE";
            case Calendar.JULY:
                return "JULY";
            case Calendar.AUGUST:
                return "AUGUST";
            case Calendar.SEPTEMBER:
                return "SEPTEMBER";
            case Calendar.OCTOBER:
                return "OCTOBER";
            case Calendar.NOVEMBER:
                return "NOVEMBER";
            case Calendar.DECEMBER:
                return "DECEMBER";
        }
        return "";
    }

    @Override
    public void onItemSelected(int position) {

    }
}
