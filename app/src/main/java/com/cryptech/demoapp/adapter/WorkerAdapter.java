package com.cryptech.demoapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.Interface.IRecyclerItemSelectedListener;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.common.Common;
import com.cryptech.demoapp.model.WorkerModel;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.ViewHolder> {

    private final Object Context;
    private List<ConstraintLayout> constraintLayouts;
    private LocalBroadcastManager localBroadcastManager;

//    Context context;
    List<WorkerModel> workerModelList;

    public WorkerAdapter(Object context, List<WorkerModel> workerModelList) {
        Context = context;
        this.workerModelList = workerModelList;
        constraintLayouts = new ArrayList<>();
        localBroadcastManager = LocalBroadcastManager.getInstance((android.content.Context) Context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worker_item_layout,parent,false);
        return new WorkerAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final WorkerAdapter.ViewHolder holder, int position) {

        holder.worker_name.setText(workerModelList.get(position).getName());
        holder.ratingBar.setRating((float) workerModelList.get(position).getRating());


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
                intent.putExtra(Common.KEY_WORKER_SELECTED,workerModelList.get(position));
                intent.putExtra(Common.KEY_STEP, 2);
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return workerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView worker_name;
        RatingBar ratingBar;
        ConstraintLayout container;

        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            this.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            worker_name = itemView.findViewById(R.id.workerName);
            ratingBar = itemView.findViewById(R.id.workerRating);
            container = itemView.findViewById(R.id.container);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            iRecyclerItemSelectedListener.onItemSelectedListener(v, getAdapterPosition());
        }
    }
}
