package com.cryptech.demoapp.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.adapter.MyRewardsAdapter;
import com.cryptech.demoapp.model.RewardModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyRewardFragment extends Fragment {


    public MyRewardFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_reward, container, false);
        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rewardsRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));
        rewardModelList.add(new RewardModel("Buy 1 get 1 free", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));
        rewardModelList.add(new RewardModel("Cashback", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));
        rewardModelList.add(new RewardModel("Buy 2 get 1 free", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));
        rewardModelList.add(new RewardModel("Cashback", "Till 2nd, June 2020","GET 20% CASHBACK on any product above Gh 200/- and below Gh 3000/-."));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return view;
    }

}
