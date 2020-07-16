package com.cryptech.demoapp.model;

import java.util.List;

public class HomePageModel {


    public static final int BANNER_SLIDER = 0;
    public static final int STRIP_AD_BANNER = 1;
    public static final int HORIZONTAL_PRODUCT_VIEW = 2;
    public static final int GRID_PRODUCT_VIEW = 3;

    private int type;
    private String backgroundColor;

    /********  BANNER SLIDER STARTS ******/

    private List<SliderModel> sliderModelList;

    public HomePageModel(int type, List<SliderModel> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }

    /********  STRIP AD SLIDER STARTS ******/

    private String resource;


    public HomePageModel(int type, String resource, String backgroundColor) {
        this.type = type;
        this.resource = resource;
        this.backgroundColor = backgroundColor;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    /********  STRIP AD SLIDER ENDS ******/


    /********  HORIZONTAL PRODUCT LAYOUT AND GRID PRODUCT LAYOUT STARTS ******/

    private String title;
    private List<HorizontalScrollProductModel> horizontalScrollProductModelList;
    private List<WishListModel> viewAllProductList;

    /********  HORIZONTAL PRODUCT LAYOUT STARTS ******/
    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalScrollProductModel> horizontalScrollProductModelList, List<WishListModel> viewAllProductList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
        this.viewAllProductList = viewAllProductList;
    }


    public List<WishListModel> getViewAllProductList() {
        return viewAllProductList;
    }

    public void setViewAllProductList(List<WishListModel> viewAllProductList) {
        this.viewAllProductList = viewAllProductList;
    }
/********  HORIZONTAL PRODUCT LAYOUT ENDS  ******/


    /********  GRID PRODUCT LAYOUT STARTS ******/
    public HomePageModel(int type, String title, String backgroundColor, List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<HorizontalScrollProductModel> getHorizontalScrollProductModelList() {
        return horizontalScrollProductModelList;
    }

    public void setHorizontalScrollProductModelList(List<HorizontalScrollProductModel> horizontalScrollProductModelList) {
        this.horizontalScrollProductModelList = horizontalScrollProductModelList;
    }


    /******** GRID PRODUCT LAYOUT ENDS  ******/
}
