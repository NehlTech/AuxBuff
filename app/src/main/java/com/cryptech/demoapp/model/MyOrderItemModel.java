package com.cryptech.demoapp.model;

public class MyOrderItemModel {

    private String productTitle;
    private String deliveryStatus;
    private int productImaage;
    private int rating;


    public MyOrderItemModel(int productImaage, int rating, String productTitle, String deliveryStatus) {
        this.productImaage = productImaage;
        this.productTitle = productTitle;
        this.rating = rating;
        this.deliveryStatus = deliveryStatus;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public int getProductImaage() {
        return productImaage;
    }

    public void setProductImaage(int productImaage) {
        this.productImaage = productImaage;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
