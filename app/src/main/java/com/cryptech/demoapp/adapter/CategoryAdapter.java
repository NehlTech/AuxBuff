package com.cryptech.demoapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cryptech.demoapp.R;
import com.cryptech.demoapp.activities.CategoryActivity;
import com.cryptech.demoapp.model.CategoryModel;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<CategoryModel> categoryModelList;


    public CategoryAdapter(List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {

        String icon = categoryModelList.get(position).getCategoryIconLink();
        String name = categoryModelList.get(position).getCategoryName();
        holder.setCategory(name, position);
        holder.setCategoryIcon(icon);

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView categoryIcon;
        private TextView categoryName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryIcon = itemView.findViewById(R.id.category_icon);
            categoryName = itemView.findViewById(R.id.category_home_name);
        }

        private void setCategoryIcon(String iconUrl) {
            //todo: set category icons here;
            if (!iconUrl.equals("null")) {

//                if (iconUrl.startsWith("http://")) {
//                    iconUrl = iconUrl.replace("http://", "https://");
//                    RequestOptions requestOptions = new RequestOptions();
//                    requestOptions.placeholder(R.drawable.home);
//                    Glide.with(itemView.getContext())
//                            .setDefaultRequestOptions(requestOptions)
//                            .load(iconUrl)
//                            .into(categoryIcon);
//                }
                Glide.with(itemView.getContext())
                        .load(iconUrl)
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.home))
                        .into(categoryIcon);
            }


        }

        private void setCategory(final String name, final int position) {
            categoryName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (position != 0) {
                        Intent categoryIntent = new Intent(itemView.getContext(), CategoryActivity.class);
                        categoryIntent.putExtra("categoryName", name);
                        itemView.getContext().startActivity(categoryIntent);
                    }
                }
            });
        }
    }
}
