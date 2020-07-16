package com.cryptech.demoapp.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.WorkerAdapter;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.model.WorkerModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep2Fragment extends Fragment {


    LocalBroadcastManager localBroadcastManager;


    private RecyclerView worker_recycler;

    private BroadcastReceiver workerDoneReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<WorkerModel> workerArrayList = intent.getParcelableArrayListExtra(Common.KEY_WORKER_LOAD_DONE);
            WorkerAdapter workerAdapter = new WorkerAdapter(getContext(),workerArrayList);
            worker_recycler.setAdapter(workerAdapter);
        }
    };

    static BookingStep2Fragment instance;

    public static BookingStep2Fragment getInstance() {
        if (instance == null)

            instance = new BookingStep2Fragment();

        return instance;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(workerDoneReceiver, new IntentFilter(Common.KEY_WORKER_LOAD_DONE));
    }

    @Override
    public void onDestroy() {

        localBroadcastManager.unregisterReceiver(workerDoneReceiver);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view =inflater.inflate(R.layout.fragment_booking_step_two,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        worker_recycler = view.findViewById(R.id.service_recycler);
        worker_recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }
}
