package com.example.lagomcurfew.activities;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lagomcurfew.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TimeSlotAdapter extends PagerAdapter {

    private Context mContext;
    private TimeSlotFragment fTimeSlotFragment;
    private ArrayList<TimeSlot> timeSlot;
    //private LayoutInflater inflater;
    private int currentItemPos;

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
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ViewHolder holder;

            //inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);

            TimeSlot date = this.timeSlot.get(position);

            View convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.time_slot_item,
                     container, false);
            holder = new ViewHolder();

            holder.dateTextView =  convertView
                    .findViewById(R.id.layout_time_slot_text);
            holder.dayTextview = convertView
                    .findViewById(R.id.layout_time_slot_day);
            holder.monthTextView =  convertView
                    .findViewById(R.id.layout_time_slot_month);

            //holder.outerLayout = convertView
            //        .findViewById(R.id.layout_date_item_outer_layout);

            convertView.setTag(Integer.valueOf(position));

            holder.dateTextView.setText(date.getDate());
            holder.dayTextview.setText(date.getDay());
            holder.monthTextView.setText(date.getMonth());

            convertView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    fTimeSlotFragment.onPagerItemClick(v,
                            (Integer) v.getTag());
                }
            });

            if (position == currentItemPos) {
                holder.outerLayout.setBackgroundColor(Color.parseColor("#EC522C"));
            } else {
                holder.outerLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            }

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

