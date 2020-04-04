package com.example.lagomcurfew.activities;


import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lagomcurfew.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TimeSlotAdapter extends PagerAdapter {

    private ArrayList<TimeSlot> timeSlot;
    private int currentItemPos;
    private LayoutInflater mLayoutInflater;
    private Button btnFirstSlot;
    private Button btnSecondSlot;
    private Button btnThirdSlot;
    private Button btnLastSlot;

        public TimeSlotAdapter(ArrayList<TimeSlot> timeSlots, onItemSelectedListener onItemSelectedListener) {
            this.onItemSelectedListener = onItemSelectedListener;
            this.timeSlot = timeSlots;
        }

        private onItemSelectedListener onItemSelectedListener;

        public void setCurrentItem(int item) {
            this.currentItemPos = item;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            ViewHolder holder;

            mLayoutInflater = LayoutInflater.from(container.getContext());

            TimeSlot date = this.timeSlot.get(position);

            View convertView =  mLayoutInflater.inflate(R.layout.time_slot_item, container, false);
           holder = new ViewHolder();

            holder.dateTextView =  convertView
                    .findViewById(R.id.time_slot_text);
            holder.dayTextview = convertView
                   .findViewById(R.id.time_slot_day);
            holder.monthTextView =  convertView
                    .findViewById(R.id.time_slot_month);


            convertView.setTag(Integer.valueOf(position));

            holder.dateTextView.setText(date.getDate() + "/" + date.getMonth());
            holder.dayTextview.setText(date.getDay());
            //holder.monthTextView.setText(date.getMonth());

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    onItemSelectedListener.onItemSelected(position);
                }
            });


            container.addView(convertView);

            return convertView;
        }

        private class ViewHolder  {
            private TextView monthTextView;
            private TextView dayTextview;
            private TextView dateTextView;

            private LinearLayout outerLayout;


        }

        @Override
        public int getCount() {
            return timeSlot.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // TODO Auto-generated method stub
            return view == (object);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
             container.removeView(view);
        }

        public float getPageWidth(int position) {
            return 0.2f;
        }

    public interface onItemSelectedListener {

        void onItemSelected(int position);
    }
    }

