package com.cryptech.demoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.model.WishListModel;

import java.util.List;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    private List<WishListModel> wishListModelList;

    public WishListAdapter(List<WishListModel> wishListModelList) {
        this.wishListModelList = wishListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wishlist_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = wishListModelList.get(position).getProductImage();
        String title = wishListModelList.get(position).getProductTitle();
        int freeCoupons = wishListModelList.get(position).getFreeCoupon();
        String rating = wishListModelList.get(position).getRating();
        int totalRatings = wishListModelList.get(position).getTotalRatings();
        String productPrice = wishListModelList.get(position).getProductPrice();
        String reducedPrice = wishListModelList.get(position).getReducedPrice();
        String paymentMethod = wishListModelList.get(position).getPaymentMethod();
        holder.setData(resource,title,freeCoupons,rating,totalRatings,productPrice,reducedPrice,paymentMethod);

    }

    @Override
    public int getItemCount() {
        return wishListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private TextView productTitle;
        private TextView freeCoupons;
        private ImageView couponIcon;
        private TextView ratings;
        private TextView totalRatings;
        private View priceCut;
        private TextView productPrice;
        private TextView reducedPrice;
        private TextView paymentMethod;
        private ImageButton deletedBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            freeCoupons = itemView.findViewById(R.id.tv_free_tag);
            couponIcon = itemView.findViewById(R.id.free_tag_icon);
            ratings = itemView.findViewById(R.id.tv_product_rating_miniview);
            totalRatings = itemView.findViewById(R.id.total_ratings);
            priceCut = itemView.findViewById(R.id.price_cut);
            productPrice = itemView.findViewById(R.id.product_price);
            reducedPrice = itemView.findViewById(R.id.reduced_price);
            paymentMethod = itemView.findViewById(R.id.payment_method);
            deletedBtn = itemView.findViewById(R.id.remove_btn);
        }

        private void setData(int resource, String title, int freeCouponsNo, String averageRate, int totalRatingsNo, String price, String reducedPriceValue, String payMethod) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCouponsNo != 0) {
                couponIcon.setVisibility(View.VISIBLE);
                if (freeCouponsNo == 1) {
                    freeCoupons.setText("free " + freeCouponsNo + " coupon");
                } else {
                    freeCoupons.setText("free " + freeCouponsNo + " coupons");
                }

            } else {
                couponIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }
            ratings.setText(averageRate);
            totalRatings.setText(totalRatingsNo + "(ratings)");
            productPrice.setText(price);
            reducedPrice.setText(reducedPriceValue);
            paymentMethod.setText(payMethod);
            deletedBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "deleted", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
