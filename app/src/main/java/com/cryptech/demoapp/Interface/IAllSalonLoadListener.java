package com.cryptech.demoapp.Interface;

import java.util.List;

public interface IAllSalonLoadListener {

    void onAllSalonLoadSucess(List<String> regionList);
    void onAllSalonLoadFailed(String message);
}
