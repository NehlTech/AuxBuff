package com.cryptech.demoapp.fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.Interface.IAllSalonLoadListener;
import com.cryptech.demoapp.Interface.IBranchLoadListerner;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.SalonAdapter;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.model.SalonModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingStep1Fragment extends Fragment implements IAllSalonLoadListener, IBranchLoadListerner {

    public BookingStep1Fragment() {
        // Required empty public constructor
    }

    CollectionReference allSalonRef;
    CollectionReference branchRef;
    IAllSalonLoadListener iAllSalonLoadListener;
    IBranchLoadListerner iBranchLoadListerner;


    private FirebaseFirestore firebaseFirestore;

    private MaterialSpinner spinner;
    private RecyclerView salon_recycler;

    Context context;

    AlertDialog alertDialog;

    static BookingStep1Fragment instance;

    public static BookingStep1Fragment getInstance() {
        if (instance == null)

            instance = new BookingStep1Fragment();

        return instance;
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseFirestore = FirebaseFirestore.getInstance();



        alertDialog = new SpotsDialog.Builder().setContext(getActivity()).build();
        allSalonRef = FirebaseFirestore.getInstance().collection("ALLSALON");
        iAllSalonLoadListener = this;
        iBranchLoadListerner = this;

//        firebaseFirestore.collection("ALLSALON").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    QuerySnapshot querySnapshot = task.getResult();
//                    listOfRegion.add("Please choose a region");
//                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                        listOfRegion.add(documentSnapshot.getId());
//                    }
//                    spinner.setItems(listOfRegion);
//                    spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
//
//                            if (position > 0) {
//                                loadBranches(item.toString());
//                            }
//                            else salon_recycler.setVisibility(View.GONE);
//
//                        }
//                    });
//                } else {
//                    String error = task.getException().getMessage();
//                   Toast.makeText(getActivity(),error,Toast.LENGTH_LONG);
//                }
//            }
//        });
    }

//    private void loadBranches(String region) {
//        final List<SalonModel> listOfBranches = new ArrayList<>();
//        alertDialog.show();
//        firebaseFirestore.collection("ALLSALON")
//                .document(region)
//                .collection("Branch")
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                if (task.isSuccessful()) {
//                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
//                        listOfBranches.add(documentSnapshot.toObject(SalonModel.class));
//                    }
//                    SalonAdapter salonAdapter = new SalonAdapter(listOfBranches);
//                    salon_recycler.setAdapter(salonAdapter);
//                    salon_recycler.setVisibility(View.VISIBLE);
//                    alertDialog.dismiss();
//                }
//                else {
//                    String error = task.getException().getMessage();
//                    Toast.makeText(getActivity(),error,Toast.LENGTH_LONG);
//                }
//            }
//
//        });
//
//
//    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_booking_step_one,container,false);

        spinner = view.findViewById(R.id.spinner);
        salon_recycler = view.findViewById(R.id.salon_recycler);
        salon_recycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        loadAllSalon();
        return view;
    }

    private void loadAllSalon() {

        allSalonRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            List<String> list = new ArrayList<>();
                            list.add("Please choose region");
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                list.add(documentSnapshot.getId());
                                iAllSalonLoadListener.onAllSalonLoadSucess(list);
                            }
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iAllSalonLoadListener.onAllSalonLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllSalonLoadSucess(List<String> regionList) {

        spinner.setItems(regionList);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

                if (position > 0) {

                    loadBranchOfRegion(item.toString());
                } else {
                    salon_recycler.setVisibility(View.GONE);
                }
            }
        });
    }

    private void loadBranchOfRegion(String branchName) {

        alertDialog.show();
        Common.region = branchName;
        branchRef = FirebaseFirestore.getInstance()
                .collection("ALLSALON")
                .document(branchName)
                .collection("Branch");
        branchRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<SalonModel> list = new ArrayList<>();
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                        SalonModel salonModel = documentSnapshot.toObject(SalonModel.class);
                        salonModel.setSalonId(documentSnapshot.getId());
                        list.add(salonModel);
                    }
                    iBranchLoadListerner.onBranchLoadSucess(list);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iBranchLoadListerner.onBranchLoadFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onAllSalonLoadFailed(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBranchLoadSucess(List<SalonModel> salonList) {

        context.getApplicationContext();
        SalonAdapter salonAdapter = new SalonAdapter(context, salonList);
        salon_recycler.setAdapter(salonAdapter);
        salon_recycler.setVisibility(View.VISIBLE);
        alertDialog.dismiss();
    }

    @Override
    public void onBranchLoadFailed(String message) {

        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        alertDialog.dismiss();
    }
}
