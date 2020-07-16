package com.cryptech.demoapp.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.CategoryAdapter;
import com.cryptech.demoapp.adapter.HomePageAdapter;
import com.cryptech.demoapp.model.HomePageModel;

import java.util.ArrayList;

import static com.cryptech.demoapp.db.Dbqueries.categoryModelList;
import static com.cryptech.demoapp.db.Dbqueries.lists;
import static com.cryptech.demoapp.db.Dbqueries.loadCategories;
import static com.cryptech.demoapp.db.Dbqueries.loadedCategoriesNames;
import static com.cryptech.demoapp.db.Dbqueries.loadFragmentData;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private HomePageAdapter homePageAdapter;
    private RecyclerView homePageRecyclerView;

    private ImageView noInternetConnection;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        noInternetConnection = view.findViewById(R.id.no_internet_connection);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected() == true) {

                noInternetConnection.setVisibility(View.GONE);
                categoryRecyclerView = view.findViewById(R.id.category_recyclerview);

                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                categoryRecyclerView.setLayoutManager(layoutManager);


                categoryAdapter = new CategoryAdapter(categoryModelList);
                categoryRecyclerView.setAdapter(categoryAdapter);

                if (categoryModelList.size() == 0) {
                    loadCategories(categoryAdapter, getContext());
                } else {
                    categoryAdapter.notifyDataSetChanged();
                }



                /********  HOMEPAGE STARTS ******/

                homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                homePageRecyclerView.setLayoutManager(linearLayoutManager);


                if (lists.size() == 0) {
                    loadedCategoriesNames.add("HOME");
                    lists.add(new ArrayList<HomePageModel>());
                    homePageAdapter = new HomePageAdapter(lists.get(0));
                    loadFragmentData(homePageAdapter, getContext(),0,"Home");
                } else {
                    homePageAdapter = new HomePageAdapter(lists.get(0));
                    homePageAdapter.notifyDataSetChanged();
                }

                homePageRecyclerView.setAdapter(homePageAdapter);

            }else{
                Glide.with(this).load(R.drawable.nointerntetconnection).into(noInternetConnection);
                noInternetConnection.setVisibility(View.VISIBLE);
            }
        }


        /********  HOMEPAGE ENDS ******/


        return view;
    }



}
