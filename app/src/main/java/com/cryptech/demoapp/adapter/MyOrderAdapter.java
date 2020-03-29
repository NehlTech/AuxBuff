package com.cryptech.demoapp.adapter;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.activities.OrderDetailsActivity;
import com.cryptech.demoapp.model.MyOrderItemModel;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {


    private List<MyOrderItemModel> myOrderItemModelList;

    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;
    }

    @NonNull
    @Override
    public MyOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        Get the layout and inflate it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_oder_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.ViewHolder holder, int position) {

        int resource = myOrderItemModelList.get(position).getProductImaage();
        int rating = myOrderItemModelList.get(position).getRating();
        String title = myOrderItemModelList.get(position).getProductTitle();
        String deliveredDate = myOrderItemModelList.get(position).getDeliveryStatus();
        holder.setData(resource, title, deliveredDate, rating);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //In the view get the various components like imaage, text and etc.
        private ImageView productImage;
        private ImageView orderIndicator;
        private TextView productTitle;
        private TextView deliveryStatus;


        private LinearLayout rateNowContainer;

      public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            orderIndicator = itemView.findViewById(R.id.order_indicator);
            productTitle = itemView.findViewById(R.id.product_title);
            deliveryStatus = itemView.findViewById(R.id.order_delivered_date);
            rateNowContainer = itemView.findViewById(R.id.rate_now_container);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent orderDetailsIntent = new Intent(itemView.getContext(), OrderDetailsActivity.class);
                    itemView.getContext().startActivity(orderDetailsIntent);
                }
            });
        }

        private void setData(int resource, String title, String deliveredDate, int rating) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (deliveredDate.equals("Cancelled")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.colorPrimary)));
                }
            }   else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        orderIndicator.setImageTintList(ColorStateList.valueOf(itemView.getContext().getResources().getColor(R.color.successfulGreen)));
                    }
                }
                deliveryStatus.setText(deliveredDate);
                setRating(rating);
                for (int x = 0; x < rateNowContainer.getChildCount(); x++) {

                    final int starPosition = x;
                    rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setRating(starPosition);
                        }
                    });

                }


        }
//        /********  RATING NOW STARTS ******/
//        private void rateNow() {
//            for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
//
//                final int starPosition = x;
//                rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        setRating(starPosition);
//                    }
//                });
//
//            }
//        }
//        /********  RATING NOW ENDS ******/

        private void setRating(int starPosition) {

            for (int x = 0; x < rateNowContainer.getChildCount(); x++) {

                ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
                    if (x <= starPosition) {
                        starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
                    }
                }
            }

        }
    }
}
