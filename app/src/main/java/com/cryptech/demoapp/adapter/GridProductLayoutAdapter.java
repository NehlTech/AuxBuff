package com.cryptech.demoapp.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.activities.ProductDetailsActivity;
import com.cryptech.demoapp.model.HorizontalScrollProductModel;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<HorizontalScrollProductModel> horizontalScrollProductModelList;

    public GridProductLayoutAdapter(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, final ViewGroup viewGroup) {
        View view;

        if (convertView == null) {

            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.setElevation(0);
            }

            view.setBackgroundColor(Color.parseColor("#ffffff"));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent productDetailsIntent = new Intent(viewGroup.getContext(), ProductDetailsActivity.class);
                    viewGroup.getContext().startActivity(productDetailsIntent);
                }
            });

            ImageView productImage = view.findViewById(R.id.h_s_productImage);
            TextView productTitle = view.findViewById(R.id.h_s_productTitle);
            TextView productDescription = view.findViewById(R.id.h_s_productDescription);
            TextView productPrice = view.findViewById(R.id.h_s_productPrice);

            productImage.setImageResource(horizontalScrollProductModelList.get(i).getProductImage());
            productTitle.setText(horizontalScrollProductModelList.get(i).getProductTitle());
            productDescription.setText(horizontalScrollProductModelList.get(i).getProductDescription());
            productPrice.setText(horizontalScrollProductModelList.get(i).getProductPrice());
        } else {

            view = convertView;
        }

        return view;
    }
}
