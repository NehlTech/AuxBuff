package com.cryptech.demoapp.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.common.Common;

import java.text.SimpleDateFormat;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep4Fragment extends Fragment {


    SimpleDateFormat simpleDateFormat;
    LocalBroadcastManager localBroadcastManager;

    private TextView booking_time;
    private TextView booking_name;
    private TextView booking_salon_address;
    private TextView booking_salon_web;
    private TextView booking_salon_phone;
    private TextView booking_salon_open_hours;


    BroadcastReceiver confirmBookingReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            setData();
        }
    };

    private void setData() {

        booking_name.setText(Common.currentWorker.getName());
        booking_time.setText(new StringBuilder(Common.convertTimeSlotToString(Common.currentTimeSlot))
        .append(" at ")
        .append(simpleDateFormat.format(Common.currentDate.getTime())));


        booking_salon_address.setText(Common.currentSalon.getAddress());
        booking_salon_web.setText(Common.currentSalon.getWebsite());
        booking_salon_phone.setText(Common.currentSalon.getPhone());
        booking_salon_open_hours.setText(Common.currentSalon.getOpenHours());

    }


    static BookingStep4Fragment instance;

    public static BookingStep4Fragment getInstance() {
        if (instance == null)

            instance = new BookingStep4Fragment();

        return instance;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(confirmBookingReceiver, new IntentFilter(Common.KEY_CONFIRM_BOOKING));
    }

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(confirmBookingReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_booking_step_four,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        booking_time = view.findViewById(R.id.booking_time);
        booking_name = view.findViewById(R.id.booking_worker);
        booking_salon_address = view.findViewById(R.id.booking_salon_address);
        booking_salon_web = view.findViewById(R.id.booking_salon_web);
        booking_salon_phone = view.findViewById(R.id.booking_salon_phone);
        booking_salon_open_hours = view.findViewById(R.id.booking_salon_open_hours);

    }
}
