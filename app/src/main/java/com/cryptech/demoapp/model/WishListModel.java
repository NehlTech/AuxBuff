package com.cryptech.demoapp.model;

public class WishListModel {

    private int productImage;
    private int freeCoupon;
    private int totalRatings;

    private String productTitle;
    private String rating;
    private String productPrice;
    private String reducedPrice;
    private String paymentMethod;

    public WishListModel(int productImage, int freeCoupon, int totalRatings, String productTitle, String rating, String productPrice, String reducedPrice, String paymentMethod) {
        this.productImage = productImage;
        this.freeCoupon = freeCoupon;
        this.totalRatings = totalRatings;
        this.productTitle = productTitle;
        this.rating = rating;
        this.productPrice = productPrice;
        this.reducedPrice = reducedPrice;
        this.paymentMethod = paymentMethod;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public int getFreeCoupon() {
        return freeCoupon;
    }

    public void setFreeCoupon(int freeCoupon) {
        this.freeCoupon = freeCoupon;
    }

    public int getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(int totalRatings) {
        this.totalRatings = totalRatings;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
