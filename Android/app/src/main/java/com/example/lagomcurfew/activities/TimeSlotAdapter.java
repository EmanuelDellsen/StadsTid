package com.example.lagomcurfew.activities;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lagomcurfew.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TimeSlotAdapter extends PagerAdapter {

    private ArrayList<TimeSlot> timeSlot;
    private int currentItemPos;
    private LayoutInflater mLayoutInflater;
    private Button btnFirstSlot;
    private Button btnSecondSlot;
    private Button btnThirdSlot;
    private Button btnFourthSlot;
    private Button btnLastSlot;
    private Context context;
    private Integer clickedBtn;
    private int indexForBackground = 0;


    public TimeSlotAdapter(ArrayList<TimeSlot> timeSlots, onItemSelectedListener onItemSelectedListener, Context context) {
            this.onItemSelectedListener = onItemSelectedListener;
            this.timeSlot = timeSlots;
            this.context = context;
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

            Toast.makeText(mLayoutInflater.getContext(), " test -> " + indexForBackground, Toast.LENGTH_LONG).show();
           holder = new ViewHolder();

            holder.dateTextView =  convertView
                    .findViewById(R.id.time_slot_text);
            holder.dayTextview = convertView
                   .findViewById(R.id.time_slot_day);

            convertView.setTag(Integer.valueOf(position));
            holder.dateTextView.setText(date.getDate() + "/" + date.getMonth());
            holder.dayTextview.setText(date.getDay());
            //holder.monthTextView.setText(date.getMonth());

            btnFirstSlot = convertView.findViewById(R.id.btn_first_slot);
            btnSecondSlot = convertView.findViewById(R.id.btn_second_slot);
            btnThirdSlot = convertView.findViewById(R.id.btn_third_slot);
            btnFourthSlot = convertView.findViewById(R.id.btn_fourth_slot);
            btnLastSlot = convertView.findViewById(R.id.btn_last_slot);


            btnFirstSlot.setActivated(true);
            btnSecondSlot.setActivated(true);
            btnThirdSlot.setActivated(true);
            btnFourthSlot.setActivated(true);
            btnLastSlot.setActivated(true);

            btnFirstSlot.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onItemSelectedListener.onItemSelected(position, 0);


                }
            });
            btnSecondSlot.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    onItemSelectedListener.onItemSelected(position, 1);
                   // Toast.makeText(mLayoutInflater.getContext(), " test -> " + clickedBtn, Toast.LENGTH_LONG).show();

                }
            });
            btnThirdSlot.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    onItemSelectedListener.onItemSelected(position,2);

                }
            });
            btnFourthSlot.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    onItemSelectedListener.onItemSelected(position,3);

                }
            });
            btnLastSlot.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                    onItemSelectedListener.onItemSelected(position,4);


                }
            });


            container.addView(convertView);

            return convertView;
        }



    private class ViewHolder {
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

        void onItemSelected(int position, int indexOfBtn);
    }
}

/*
for(;indexForBackground < 365; indexForBackground++){
                if(indexForBackground%2 == 0){
                    convertView.setBackgroundColor(ContextCompat.getColor(context,R.color.off_white));
                    Log.i("TIME","inside white colour");
                } else {
                    Log.i("TIME","inside red colour");
                    convertView.setBackgroundColor(ContextCompat.getColor(context,R.color.light_grey));
                }
            }
 */
