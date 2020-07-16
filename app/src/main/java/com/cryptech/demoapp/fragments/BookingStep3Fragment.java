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
import android.app.AlertDialog;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.Interface.iTimeSlotLoadListener;
import com.cryptech.demoapp.adapter.TimeSlotAdapter;
import com.cryptech.demoapp.adapter.WorkerAdapter;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.model.TimeSlotModel;
import com.cryptech.demoapp.model.WorkerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep3Fragment extends Fragment implements com.cryptech.demoapp.Interface.iTimeSlotLoadListener {


    DocumentReference workerDocRef;
    iTimeSlotLoadListener iTimeSlotLoadListener;
    AlertDialog alertDialog;
    SimpleDateFormat simpleDateFormat;

    LocalBroadcastManager localBroadcastManager;


    private RecyclerView time_slot_recycler;
    private HorizontalCalendarView horizontalCalendar;


    static BookingStep3Fragment instance;

    public static BookingStep3Fragment getInstance() {
        if (instance == null)

            instance = new BookingStep3Fragment();

        return instance;
    }


    private BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE,0);
            loadAvailableTimeSlotOfWorkers(Common.currentWorker.getWorkerId(),simpleDateFormat.format(date.getTime()));


        }
    };

    private void loadAvailableTimeSlotOfWorkers(String workerId, final String bookdate) {

            alertDialog.show();

//            /ALLSALON/Accra/Branch/LBujpAjpVqMG48rWEXGn/Worker/TejrQIJKqUZoMh48zwY5
        workerDocRef = FirebaseFirestore.getInstance()
                        .collection("ALLSALON")
                        .document(Common.region)
                        .collection("Branch")
                        .document(Common.currentSalon.getSalonId())
                        .collection("Worker")
                        .document(Common.currentWorker.getWorkerId());
        workerDocRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {

                    final DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {

                        CollectionReference date = FirebaseFirestore.getInstance()
                                .collection("ALLSALON")
                                .document(Common.region)
                                .collection("Branch")
                                .document(Common.currentSalon.getSalonId())
                                .collection("Worker")
                                .document(Common.currentWorker.getWorkerId())
                                .collection(bookdate);


                        date.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                if (task.isSuccessful()) {

                                    QuerySnapshot querySnapshot = task.getResult();
                                    if (querySnapshot.isEmpty()) {

                                        iTimeSlotLoadListener.onTimeSlotLoadEmpty();
                                    } else {

                                        List<TimeSlotModel> timeSlotModelList = new ArrayList<>();
                                        for (QueryDocumentSnapshot documentSnapshot1 : task.getResult()) {
                                            timeSlotModelList.add(documentSnapshot1.toObject(TimeSlotModel.class));
                                            iTimeSlotLoadListener.onTimeSlotLoadSuccess(timeSlotModelList);
                                        }
                                    }
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                iTimeSlotLoadListener.onTimeSlotLoadFailed(e.getMessage());
                            }
                        });
                    }
                }
            }
        });


    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iTimeSlotLoadListener = this;
        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(displayTimeSlot, new IntentFilter(Common.KEY_DISPLAY_TIME_SLOT));

        simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");
        alertDialog = new SpotsDialog.Builder().setContext(getContext()).setCancelable(false).build();

    }

    @Override
    public void onDestroy() {

        localBroadcastManager.unregisterReceiver(displayTimeSlot);
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_booking_step_three,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        time_slot_recycler = view.findViewById(R.id.time_slot_recycler);
        horizontalCalendar = view.findViewById(R.id.calendarView);
        time_slot_recycler.setLayoutManager(new GridLayoutManager(getActivity(),3));

        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, 0);

        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view,R.id.calendarView)
                                                            .range(startDate,endDate)
                                                            .datesNumberOnScreen(1)
                                                            .mode(HorizontalCalendar.Mode.DAYS)
                                                            .defaultSelectedDate(startDate)
                                                            .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {

                if (Common.currentDate.getTimeInMillis() != date.getTimeInMillis()) {
                    Common.currentDate = date;
                    loadAvailableTimeSlotOfWorkers(Common.currentWorker.getWorkerId(),simpleDateFormat.format(date.getTime()));
                }
            }
        });
    }

    @Override
    public void onTimeSlotLoadSuccess(List<TimeSlotModel> timeSlotModelList) {
        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(getContext(),timeSlotModelList);
        time_slot_recycler.setAdapter(timeSlotAdapter);
        alertDialog.dismiss();
    }

    @Override
    public void onTimeSlotLoadFailed(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        alertDialog.dismiss();
    }

    @Override
    public void onTimeSlotLoadEmpty() {

        TimeSlotAdapter timeSlotAdapter = new TimeSlotAdapter(getContext());
        time_slot_recycler.setAdapter(timeSlotAdapter);
        alertDialog.dismiss();
    }
}
