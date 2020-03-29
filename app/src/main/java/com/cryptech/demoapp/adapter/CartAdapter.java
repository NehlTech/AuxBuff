package com.cryptech.demoapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptech.demoapp.R;
import com.cryptech.demoapp.model.CartItemModel;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter {


    private List<CartItemModel> cartItemModelList;

    public CartAdapter(List<CartItemModel> cartItemModelList) {
        this.cartItemModelList = cartItemModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (cartItemModelList.get(position).getType()) {
            case 0:
                return CartItemModel.CART_ITEM;
            case 1:
                return CartItemModel.TOTAL_AMOUNT;
            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case CartItemModel.CART_ITEM:
                View cartItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                return new CartItemViewHolder(cartItemView);
            case CartItemModel.TOTAL_AMOUNT:
                View cartTotalAmountView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_total_amount_layout, parent, false);
                return new CartTotalAmountViewHolder(cartTotalAmountView);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (cartItemModelList.get(position).getType()) {
            case CartItemModel.CART_ITEM:
                int resource = cartItemModelList.get(position).getProductImage();
                String title = cartItemModelList.get(position).getProductTitle();
                int freeCoupons = cartItemModelList.get(position).getFreeCoupons();
                String productPrice = cartItemModelList.get(position).getProductPrice();
                String reducedPrice = cartItemModelList.get(position).getReducedPrice();
                int offersApplied = cartItemModelList.get(position).getOffersApplied();

                ((CartItemViewHolder)holder).setItemDetails(resource, title, freeCoupons,productPrice,reducedPrice,offersApplied);
                break;

                case CartItemModel.TOTAL_AMOUNT:
                    String totalItems = cartItemModelList.get(position).getTotalItems();
                    String totalItemsPrice = cartItemModelList.get(position).getTotalItemPrice();
                    String deliveryPrice = cartItemModelList.get(position).getDeliveryPrice();
                    String totalAmount = cartItemModelList.get(position).getTotalAmount();
                    String saveAmount = cartItemModelList.get(position).getSavedAmount();

                    ((CartTotalAmountViewHolder)holder).setTotalAmount(totalItems,totalItemsPrice,deliveryPrice,totalAmount,saveAmount);
                    break;
                    default:
                        return;
        }

    }

    @Override
    public int getItemCount() {
        return cartItemModelList.size();
    }

    class CartItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView productImage;
        private ImageView freeCouponIcon;
        private TextView productTitle;
        private TextView productPrice;
        private TextView reducedPrice;
        private TextView freeCoupons;
        private TextView productQty;
        private TextView offersApplied;
        private TextView couponsApplied;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            freeCouponIcon = itemView.findViewById(R.id.free_tag_icon);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
            reducedPrice = itemView.findViewById(R.id.reduced_price);
            freeCoupons = itemView.findViewById(R.id.tv_free_tag);
            productQty = itemView.findViewById(R.id.product_quantity);
            offersApplied = itemView.findViewById(R.id.offer_applied);
            couponsApplied = itemView.findViewById(R.id.coupon_applied);
        }

        private void setItemDetails(int resource, String title, int freeCouponNo, String productPriceText, String reducedPriceText, int offerAplliedNo) {
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if (freeCouponNo > 0) {
                freeCouponIcon.setVisibility(View.VISIBLE);
                freeCoupons.setVisibility(View.VISIBLE);
                if (freeCouponNo == 1) {
                    freeCoupons.setText("Free " + freeCouponNo + " Coupon");
                } else {
                    freeCoupons.setText("Free " + freeCouponNo + " Coupons");
                }
            } else {
                freeCouponIcon.setVisibility(View.INVISIBLE);
                freeCoupons.setVisibility(View.INVISIBLE);
            }
                productPrice.setText(productPriceText);
                reducedPrice.setText(reducedPriceText);

            if (offerAplliedNo > 0) {
                offersApplied.setVisibility(View.VISIBLE);
                offersApplied.setText(offerAplliedNo + " Offers applied");
            } else {
                offersApplied.setVisibility(View.INVISIBLE);
            }
        }
    }

    class CartTotalAmountViewHolder extends RecyclerView.ViewHolder {

        private TextView totalItems;
        private TextView totalItemPrice;
        private TextView deliveryPrice;
        private TextView totalAmount;
        private TextView savedAmount;

        public CartTotalAmountViewHolder(@NonNull View itemView) {
            super(itemView);

            totalItems = itemView.findViewById(R.id.total_items);
            totalItemPrice = itemView.findViewById(R.id.total_items_price);
            deliveryPrice = itemView.findViewById(R.id.delivery_price);
            totalAmount = itemView.findViewById(R.id.total_price);
            savedAmount = itemView.findViewById(R.id.saved_amount);
        }

        private void setTotalAmount(String totalItemsText, String totalItemPriceText, String delieveryPriceText, String totalAmountText, String savedAmountText) {
            totalItems.setText(totalItemsText);
            totalItemPrice.setText(totalItemPriceText);
            deliveryPrice.setText(delieveryPriceText);
            totalAmount.setText(totalAmountText);
            savedAmount.setText(savedAmountText);
        }
    }
}
