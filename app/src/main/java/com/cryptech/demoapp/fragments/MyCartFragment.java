package com.cryptech.demoapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.CartAdapter;
import com.cryptech.demoapp.model.CartItemModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.phone4, "GALAXY S7",2,"Gh 49999/-","Gh 6999/-",1,0,0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.phone4, "GALAXY S7",0,"Gh 49999/-","Gh 6999/-",1,1,0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.phone4, "GALAXY S7",2,"Gh 49999/-","Gh 6999/-",1,2,0));
        cartItemModelList.add(new CartItemModel(1, "Price (3 items)","Gh 169999", "Free","Gh 45999/-","Gh 5999/-"));
        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        return view;
    }

}
