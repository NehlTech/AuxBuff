package com.cryptech.demoapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.WishListAdapter;
import com.cryptech.demoapp.model.WishListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishListFragment extends Fragment {


    public MyWishListFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishlistRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_wish_list, container, false);

        wishlistRecyclerView = view.findViewById(R.id.my_wish_list_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishListModel> wishListModelList = new ArrayList<>();
        wishListModelList.add(new WishListModel(R.drawable.phone4, 1, 156,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone1, 0, 166,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone2, 4, 15,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone5, 2, 156,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone1, 8, 11,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone1, 0, 190,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone4, 3, 156,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone2, 2, 2998,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone3, 0, 156,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone5, 6, 2,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone1, 1, 156,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone5, 0, 156,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone4, 1, 1,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone1, 1, 11,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));
        wishListModelList.add(new WishListModel(R.drawable.phone2, 0, 0,"Pixel 2", "4", "Gh 14999/-", "Gh 599/-", "Cash on delivery"));

        WishListAdapter wishListAdapter = new WishListAdapter(wishListModelList);
        wishlistRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();
        return view;
    }

}
