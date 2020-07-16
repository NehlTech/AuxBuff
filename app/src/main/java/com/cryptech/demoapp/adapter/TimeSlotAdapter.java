package com.cryptech.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.Interface.IRecyclerItemSelectedListener;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.model.TimeSlotModel;

import java.util.ArrayList;
import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {


    Context context;
    List<TimeSlotModel> timeSlotModelList;

    private List<ConstraintLayout> constraintLayouts;
    private LocalBroadcastManager localBroadcastManager;

    public TimeSlotAdapter(Context context) {
        this.context = context;
        this.timeSlotModelList = new ArrayList<>();
        constraintLayouts = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    public TimeSlotAdapter(Context context, List<TimeSlotModel> timeSlotModelList) {
        this.context = context;
        this.timeSlotModelList = timeSlotModelList;
        constraintLayouts = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_slot_layout,parent,false);
        return new TimeSlotAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.time_slot.setText(new StringBuilder(Common.convertTimeSlotToString(position)).toString());
        if (timeSlotModelList.size() == 0) {
            holder.time_slot_container.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            holder.time_slot_description.setText("Available");
            holder.time_slot_description.setTextColor(context.getResources().getColor(android.R.color.holo_green_dark));
            holder.time_slot.setTextColor(context.getResources().getColor(android.R.color.black));
        } else {
            for (TimeSlotModel slotValue : timeSlotModelList) {

                int slot = Integer.parseInt(slotValue.getSlot().toString());

                if (slot == position) {

                    holder.time_slot_container.setTag(Common.DISABLE_TAG);
                    holder.time_slot_container.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_dark));
                    holder.time_slot_description.setText("Booked");
                    holder.time_slot_description.setTextColor(context.getResources().getColor(android.R.color.white));
                    holder.time_slot.setTextColor(context.getResources().getColor(android.R.color.holo_blue_bright));
                }
            }
        }

        if (!constraintLayouts.contains(holder.time_slot_container)) {
            constraintLayouts.add(holder.time_slot_container);
            holder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
                @Override
                public void onItemSelectedListener(View view, int position) {
                    for (ConstraintLayout constraintLayout : constraintLayouts) {
                        if (constraintLayout.getTag() == null) {
                            constraintLayout.setBackgroundColor(context.getResources()
                            .getColor(android.R.color.white));
                        }
                        holder.time_slot_container.setBackgroundColor(context.getResources()
                        .getColor(android.R.color.holo_red_dark));

                        Intent intent = new Intent(Common.KEY_ENABLED_BUTTON_NEXT);
                        intent.putExtra(Common.KEY_TIME_SLOT,position);
                        intent.putExtra(Common.KEY_STEP, 3);
                        localBroadcastManager.sendBroadcast(intent);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return Common.TIME_SLOT_TOTAL;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView time_slot;
        private TextView time_slot_description;
        private ConstraintLayout time_slot_container;

        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            time_slot = itemView.findViewById(R.id.time_slot);
            time_slot_description = itemView.findViewById(R.id.time_slot_description);
            time_slot_container = itemView.findViewById(R.id.time_slot_container);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecyclerItemSelectedListener.onItemSelectedListener(v,getAdapterPosition());
        }
    }
}
