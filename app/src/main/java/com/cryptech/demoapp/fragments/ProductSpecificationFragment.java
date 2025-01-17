package com.cryptech.demoapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.ProductSpecificationAdapter;
import com.cryptech.demoapp.model.ProductSpecificationModel;

import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class ProductSpecificationFragment extends Fragment {

    private RecyclerView productSpecificationRecyclerView;
    public List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);
        productSpecificationRecyclerView = view.findViewById(R.id.product_specification_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productSpecificationRecyclerView.setLayoutManager(linearLayoutManager);
        setProductSpecificationRecyclerAdapter();
        return view;
    }

    private void setProductSpecificationRecyclerAdapter() {

//        productSpecificationModelList.add(new ProductSpecificationModel(0,"General"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//
//        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//
//        productSpecificationModelList.add(new ProductSpecificationModel(0,"General"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//
//        productSpecificationModelList.add(new ProductSpecificationModel(0,"Display"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));
//        productSpecificationModelList.add(new ProductSpecificationModel(1,"Ram", "4GB"));




        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();
    }

}
