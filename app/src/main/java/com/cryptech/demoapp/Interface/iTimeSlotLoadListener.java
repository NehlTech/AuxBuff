package com.cryptech.demoapp.Interface;

import com.cryptech.demoapp.model.TimeSlotModel;

import java.util.List;

public interface iTimeSlotLoadListener {

    void onTimeSlotLoadSuccess(List<TimeSlotModel> timeSlotModelList);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotLoadEmpty();
}
