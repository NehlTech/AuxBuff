package com.cryptech.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.Interface.IRecyclerItemSelectedListener;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.model.SalonModel;

import java.util.ArrayList;
import java.util.List;

public class SalonAdapter extends RecyclerView.Adapter<SalonAdapter.ViewHolder> {


    private final Object Context;
    private List<SalonModel> salonModelList;
    private List<ConstraintLayout> constraintLayouts;
    private LocalBroadcastManager localBroadcastManager;

    Context context;



    @NonNull
    @Override
    public SalonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_item_layout,parent,false);
        return new SalonAdapter.ViewHolder(view);
    }

    public SalonAdapter(Object context, List<SalonModel> salonModelList) {
        Context = context;
        this.salonModelList = salonModelList;
        constraintLayouts = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance((android.content.Context) Context);
    }

    @Override
    public void onBindViewHolder(@NonNull final SalonAdapter.ViewHolder holder, int position) {

        holder.salonName.setText(salonModelList.get(position).getName());
        holder.salonAddress.setText(salonModelList.get(position).getAddress());
        holder.salonImage.setImageResource(R.drawable.salon);

        if (!constraintLayouts.contains(holder.container)) {
            constraintLayouts.add(holder.container);
        }

        holder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int position) {

                for (ConstraintLayout constraintLayout : constraintLayouts) {
                    constraintLayout.setBackgroundColor(view.getResources().getColor(android.R.color.white));
                }
                holder.container.setBackgroundColor(view.getResources().getColor(android.R.color.holo_red_dark));


                Intent intent = new Intent(Common.KEY_ENABLED_BUTTON_NEXT);
                intent.putExtra(Common.KEY_SAVE,salonModelList.get(position));
                intent.putExtra(Common.KEY_STEP, 1);
                localBroadcastManager.sendBroadcast(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return salonModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView salonName;
        private TextView salonAddress;
        private ImageView salonImage;
        ConstraintLayout container;


        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            salonName = itemView.findViewById(R.id.workerName);
            salonAddress = itemView.findViewById(R.id.salonAddress);
            salonImage = itemView.findViewById(R.id.workerImage);
            container = itemView.findViewById(R.id.container);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            iRecyclerItemSelectedListener.onItemSelectedListener(v, getAdapterPosition());
        }
    }
}
