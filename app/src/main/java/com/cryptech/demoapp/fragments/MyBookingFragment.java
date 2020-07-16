package com.cryptech.demoapp.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import android.app.AlertDialog;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.BookingAdapter;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.common.NonSwipeViewPager;
import com.cryptech.demoapp.model.WorkerModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyBookingFragment extends Fragment {

LocalBroadcastManager localBroadcastManager;
AlertDialog alertDialog;
CollectionReference workerRef;

    public MyBookingFragment() {
        // Required empty public constructor
    }


    private StepView stepView;
    private Button btn_previous_step, btn_next_step;
    private NonSwipeViewPager viewPager;



    private BroadcastReceiver btn_next_receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int step = intent.getIntExtra(Common.KEY_STEP,0);

            if (step == 1) {

                Common.currentSalon = intent.getParcelableExtra(Common.KEY_SAVE);
            } else if (step == 2) {
                Common.currentWorker = intent.getParcelableExtra(Common.KEY_WORKER_SELECTED);
            }else if (step == 3) {
                Common.currentTimeSlot = intent.getIntExtra(Common.KEY_TIME_SLOT, -1);
            }
            btn_next_step.setEnabled(true);
            setColorButton();

        }
    };

    @Override
    public void onDestroy() {
        localBroadcastManager.unregisterReceiver(btn_next_receiver);
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_booking, container, false);
//        context = getActivity();
        stepView = view.findViewById(R.id.step_view);
        btn_next_step = view.findViewById(R.id.btn_next_step);
        btn_previous_step = view.findViewById(R.id.btn_prev_step);
        viewPager = view.findViewById(R.id.bookingViewPager);

        btn_previous_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Common.step == 3 || Common.step > 0) {

                    Common.step--;
                    viewPager.setCurrentItem(Common.step);
                }
            }
        });
        btn_next_step.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Common.step < 3 || Common.step == 0) {

                    Common.step++;
                    if (Common.step == 1) {
                        if (Common.currentSalon != null) {
                            loadWorkersBySalon(Common.currentSalon.getSalonId());
                        }

                    } else if (Common.step == 2) {

                        if (Common.currentWorker != null) {
                            loadTimeSlotWorker(Common.currentWorker.getWorkerId());
                        }
                    }else if (Common.step == 3) {

                        if (Common.currentTimeSlot != -1) {
                            confirmBooking();
                        }
                    }
                    viewPager.setCurrentItem(Common.step);
                }

            }
            private void loadWorkersBySalon(String salonId) {
//      /ALLSALON/Accra/Branch/LBujpAjpVqMG48rWEXGn/Worker
                alertDialog.show();

                if (!TextUtils.isEmpty(Common.region)) {

                    workerRef = FirebaseFirestore.getInstance()
                            .collection("ALLSALON")
                            .document(Common.region)
                            .collection("Branch")
                            .document(salonId)
                            .collection("Worker");

                    workerRef.get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                    ArrayList<WorkerModel> workers = new ArrayList<>();
                                    for (QueryDocumentSnapshot workersSnapshot : task.getResult()) {

                                        WorkerModel worker = workersSnapshot.toObject(WorkerModel.class);
                                        worker.setPassword("");
                                        worker.setWorkerId(workersSnapshot.getId());

                                        workers.add(worker);
                                    }

                                    Intent intent = new Intent(Common.KEY_WORKER_LOAD_DONE);
                                    intent.putParcelableArrayListExtra(Common.KEY_WORKER_LOAD_DONE, workers);
                                    localBroadcastManager.sendBroadcast(intent);
                                    alertDialog.dismiss();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            alertDialog.dismiss();
                        }
                    });
                }


            }
        });



//        bookingRecyclerView = view.findViewById(R.id.my_booking_recycler_view);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//       bookingRecyclerView.setLayoutManager(linearLayoutManager);
//
//        List<BookingInformationModel> bookingModelList = new ArrayList<>();
//
//       BookingAdapter bookingAdapter = new BookingAdapter(bookingModelList,true);
//        bookingRecyclerView.setAdapter(bookingAdapter);
//        bookingAdapter.notifyDataSetChanged();    di
        alertDialog = new SpotsDialog.Builder().setContext(getActivity()).build();
        localBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        localBroadcastManager.registerReceiver(btn_next_receiver,new IntentFilter(Common.KEY_ENABLED_BUTTON_NEXT));

        setUpStepView();
        setColorButton();

        viewPager.setAdapter(new BookingAdapter(getFragmentManager()));
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                stepView.go(position,true);
                if (position == 0) {
                    btn_previous_step.setEnabled(false);

                } else

                    btn_next_step.setEnabled(false);
                btn_previous_step.setEnabled(true);

                setColorButton();
            }



            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }

    private void confirmBooking() {

        Intent intent = new Intent(Common.KEY_CONFIRM_BOOKING);
        localBroadcastManager.sendBroadcast(intent);
    }

    private void loadTimeSlotWorker(String workerId) {

        Intent intent = new Intent(Common.KEY_DISPLAY_TIME_SLOT);
        localBroadcastManager.sendBroadcast(intent);
    }


    private void setUpStepView() {

        List<String> stepList = new ArrayList<>();
        stepList.add("Salon");
        stepList.add("Service");
        stepList.add("Time");
        stepList.add("Confirm");
        stepView.setSteps(stepList);
    }

    private void setColorButton() {
        if (btn_next_step.isEnabled()) {
            btn_next_step.setBackgroundResource(R.color.btnRed);
        } else {
            btn_next_step.setBackgroundResource(android.R.color.darker_gray);
        }

        if (btn_previous_step.isEnabled()) {
            btn_previous_step.setBackgroundResource(R.color.btnRed);
        } else {
            btn_previous_step.setBackgroundResource(android.R.color.darker_gray);
        }
    }


}
