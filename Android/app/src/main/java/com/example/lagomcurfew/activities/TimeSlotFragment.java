package com.example.lagomcurfew.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    private ImageButton btnAcceptTimeslot;
    private int indexForBackground = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mInterfaceMainActivity = (InterfaceMainActivity) getActivity();
        mMainActivity = (MainActivity) getActivity();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         retView = inflater.inflate(R.layout.fragment_time_slot, container, false);

         getDates();
         initViewPager();
         btnAcceptTimeslot = retView.findViewById(R.id.accept_booking_btn);
         btnAcceptTimeslot.setActivated(true);
         btnAcceptTimeslot.setOnClickListener(this);

        return retView;
    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.accept_booking_btn:{
                mMainActivity.getSupportFragmentManager().beginTransaction().remove(TimeSlotFragment.this).commit();
                mMainActivity.getSupportFragmentManager().popBackStack();

            }
        }
    }

    private void initViewPager() {
        timeSlotAdapter = new TimeSlotAdapter( timeSlots, this, mMainActivity.getContext());
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

            date.setFormattedDate(calendar.get(Calendar.DATE));

            OneSlot slot1 = new OneSlot();
            slot1.setStartTime(Long.valueOf("0900"));
            slot1.setDate(date.getDate());
            OneSlot slot2 = new OneSlot();
            slot2.setStartTime(Long.valueOf("1200"));
            slot2.setDate(date.getDate());
            OneSlot slot3 = new OneSlot();
            slot3.setStartTime(Long.valueOf("1500"));
            slot3.setDate(date.getDate());
            OneSlot slot4 = new OneSlot();
            slot4.setStartTime(Long.valueOf("1800"));
            slot4.setDate(date.getDate());
            OneSlot slot5 = new OneSlot();
            slot5.setStartTime(Long.valueOf("2100"));
            slot5.setDate(date.getDate());
            date.addSlot(slot1);
            date.addSlot(slot2);
            date.addSlot(slot3);
            date.addSlot(slot4);
            date.addSlot(slot5);

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
                return "1";
            case Calendar.FEBRUARY:
                return "2";
            case Calendar.MARCH:
                return "3";
            case Calendar.APRIL:
                return "4";
            case Calendar.MAY:
                return "5";
            case Calendar.JUNE:
                return "6";
            case Calendar.JULY:
                return "7";
            case Calendar.AUGUST:
                return "8";
            case Calendar.SEPTEMBER:
                return "9";
            case Calendar.OCTOBER:
                return "10";
            case Calendar.NOVEMBER:
                return "11";
            case Calendar.DECEMBER:
                return "12";
        }
        return "";
    }

    public Date getDate(int year, int month, int day, String hour) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    @Override
    public void onItemSelected(int position, int indexOfBtn) {
        TimeSlot testTimeSlot=timeSlots.get(position);

        Date date = getDate(Integer.parseInt(testTimeSlot.getYear()), Integer.parseInt(testTimeSlot.getMonth()) - 1, Integer.parseInt(testTimeSlot.getDate()),Long.toString((testTimeSlot.getSlots().get(indexOfBtn).getStartTime()) / 100));

        //Set shared preference
        mInterfaceMainActivity.saveSharedBooking(date);

    }
}
