package com.cryptech.demoapp.model;

public class CartItemModel {

    public static final int CART_ITEM = 0;
    public static final int  TOTAL_AMOUNT = 1;

    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    /******CART ITEM STARTS***/

    private String productTitle;
    private String productPrice;
    private String reducedPrice;

    private int productImage;
    private int freeCoupons;
    private int productQuantity;
    private int offersApplied;
    private int couponsApplied;

    public CartItemModel(int type, int productImage, String productTitle,int freeCoupons, String productPrice, String reducedPrice,  int productQuantity, int offersApplied, int couponsApplied) {
        this.type = type;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.reducedPrice = reducedPrice;
        this.productImage = productImage;
        this.freeCoupons = freeCoupons;
        this.productQuantity = productQuantity;
        this.offersApplied = offersApplied;
        this.couponsApplied = couponsApplied;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getReducedPrice() {
        return reducedPrice;
    }

    public void setReducedPrice(String reducedPrice) {
        this.reducedPrice = reducedPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getFreeCoupons() {
        return freeCoupons;
    }

    public void setFreeCoupons(int freeCoupons) {
        this.freeCoupons = freeCoupons;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getOffersApplied() {
        return offersApplied;
    }

    public void setOffersApplied(int offersApplied) {
        this.offersApplied = offersApplied;
    }

    public int getCouponsApplied() {
        return couponsApplied;
    }

    public void setCouponsApplied(int couponsApplied) {
        this.couponsApplied = couponsApplied;
    }


    /******CART ITEM ENDS***/


    /******CART TOTAL AMOUNT STARTS***/
    private String totalItems;
    private String totalItemPrice;
    private String deliveryPrice;
    private String savedAmount;
    private String totalAmount;

    public CartItemModel(int type, String totalItems, String totalItemPrice, String deliveryPrice, String totalAmount, String savedAmount) {
        this.type = type;
        this.totalItems = totalItems;
        this.totalItemPrice = totalItemPrice;
        this.deliveryPrice = deliveryPrice;
        this.savedAmount = savedAmount;
        this.totalAmount = totalAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(String totalItems) {
        this.totalItems = totalItems;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(String totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSavedAmount() {
        return savedAmount;
    }

    public void setSavedAmount(String savedAmount) {
        this.savedAmount = savedAmount;
    }


    /******CART TOTAL AMOUNT ENDS***/
}
