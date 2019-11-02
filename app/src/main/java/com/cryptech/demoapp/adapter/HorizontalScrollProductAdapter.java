package com.cryptech.demoapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.activities.ProductDetailsActivity;
import com.cryptech.demoapp.model.HorizontalScrollProductModel;

import java.util.List;

public class HorizontalScrollProductAdapter extends RecyclerView.Adapter<HorizontalScrollProductAdapter.ViewHolder> {

   private List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public HorizontalScrollProductAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @NonNull
    @Override
    public HorizontalScrollProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_scroll_item_layout, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalScrollProductAdapter.ViewHolder holder, int position) {

//        int resource = horizontalScrollProductModelList.get(position).getProductImage();
//        String title = horizontalScrollProductModelList.get(position).getProductTitle();
//        String description = horizontalScrollProductModelList.get(position).getProductDescription();
//        String price = horizontalScrollProductModelList.get(position).getProductPrice();
//
//        holder.setProductImage(resource);
//        holder.setProductTitle(title);
//        holder.setProductDescription(description);
//        holder.setProductPrice(price);


        final ViewHolder viewHolder =holder;
        HorizontalScrollProductModel horizontalScrollProductModel = horizontalScrollProductModelList.get(position);
        viewHolder.setProductImage(horizontalScrollProductModel.getProductImage());
        viewHolder.setProductTitle(horizontalScrollProductModel.getProductTitle());
        viewHolder.setProductDescription(horizontalScrollProductModel.getProductDescription());
        viewHolder.setProductPrice(horizontalScrollProductModel.getProductPrice());

    }

    @Override
    public int getItemCount() {

        if (horizontalScrollProductModelList.size() > 8) {
            return 8;
        } else {

            return horizontalScrollProductModelList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView productDescription;
        private TextView productPrice;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.h_s_productImage);
            productTitle = itemView.findViewById(R.id.h_s_productTitle);
            productDescription = itemView.findViewById(R.id.h_s_productDescription);
            productPrice = itemView.findViewById(R.id.h_s_productPrice);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent = new Intent(itemView.getContext(), ProductDetailsActivity.class);
                    itemView.getContext().startActivity(productDetailsIntent);
                }
            });
        }

        private void setProductImage(int resource) {
            productImage.setImageResource(resource);
        }

        private void setProductTitle(String title) {
            productTitle.setText(title);
        }

        private void setProductDescription(String description) {
            productDescription.setText(description);
        }

        private void setProductPrice(String price) {
            productPrice.setText(price);
        }
    }
}
