package com.cryptech.demoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.model.ProductSpecificationModel;

import java.util.List;

public class ProductSpecificationAdapter extends RecyclerView.Adapter<ProductSpecificationAdapter.ViewHolder> {

    private List<ProductSpecificationModel> productSpecificationModelList;

    public ProductSpecificationAdapter(List<ProductSpecificationModel> productSpecificationModelList) {
        this.productSpecificationModelList = productSpecificationModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (productSpecificationModelList.get(position).getType()) {
            case 0:

                return ProductSpecificationModel.SPECIFICATION_TITLE;
            case 1:

                return ProductSpecificationModel.SPECIFICATION_BODY;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public ProductSpecificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:

                TextView title = new TextView(parent.getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setTextColor(Color.parseColor("#000000"));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(setDp(16, parent.getContext()), setDp(16, parent.getContext()),
                        setDp(16, parent.getContext()), setDp(8, parent.getContext()));
                title.setLayoutParams(layoutParams);
                return new ViewHolder(title);

            case ProductSpecificationModel.SPECIFICATION_BODY:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_specification_item_layout, parent, false);
                return new ViewHolder(view);
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull ProductSpecificationAdapter.ViewHolder holder, int position) {

        switch (productSpecificationModelList.get(position).getType()) {
            case ProductSpecificationModel.SPECIFICATION_TITLE:

                holder.setTitle(productSpecificationModelList.get(position).getTitle());
                break;
            case ProductSpecificationModel.SPECIFICATION_BODY:
                String featureTitle = productSpecificationModelList.get(position).getFeatureName();
                String featureDetail = productSpecificationModelList.get(position).getFeatureValue();

                holder.setFeatures(featureTitle, featureDetail);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return productSpecificationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_featureName;
        private TextView tv_featureValue;
        private TextView tv_title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }

        private void setTitle(String titleText) {
            tv_title = (TextView) itemView;
            tv_title.setText(titleText);
        }

        private void setFeatures(String featureTitle, String featureDetail) {
            tv_featureName = itemView.findViewById(R.id.tv_feature_name);
            tv_featureValue = itemView.findViewById(R.id.tv_feature_value);
            tv_featureName.setText(featureTitle);
            tv_featureValue.setText(featureDetail);
        }
    }

    private int setDp(int dp, Context context) {

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
