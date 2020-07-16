package com.cryptech.demoapp.Interface;

import com.cryptech.demoapp.model.SalonModel;

import java.util.List;

public interface IBranchLoadListerner {

    void onBranchLoadSucess(List<SalonModel> salonList);
    void onBranchLoadFailed(String message);
}
