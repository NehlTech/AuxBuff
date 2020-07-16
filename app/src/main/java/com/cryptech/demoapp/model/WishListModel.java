package com.cryptech.demoapp.model;

public class WishListModel {

    private String productImage;
    private long freeCoupon;
    private long totalRatings;

    private String productTitle;
    private String rating;
    private String productPrice;
    private String reducedPrice;
    private Boolean COD;

    public WishListModel(String productImage, long freeCoupon, long totalRatings, String productTitle, String rating, String productPrice, String reducedPrice, Boolean COD) {
        this.productImage = productImage;
        this.freeCoupon = freeCoupon;
        this.totalRatings = totalRatings;
        this.productTitle = productTitle;
        this.rating = rating;
        this.productPrice = productPrice;
        this.reducedPrice = reducedPrice;
        this.COD = COD;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public long getFreeCoupon() {
        return freeCoupon;
    }

    public void setFreeCoupon(long freeCoupon) {
        this.freeCoupon = freeCoupon;
    }

    public long getTotalRatings() {
        return totalRatings;
    }

    public void setTotalRatings(long totalRatings) {
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

    public Boolean getCOD() {
        return COD;
    }

    public void setCOD(Boolean COD) {
        this.COD = COD;
    }
}
