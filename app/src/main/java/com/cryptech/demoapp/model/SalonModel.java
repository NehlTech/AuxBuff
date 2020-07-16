package com.cryptech.demoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SalonModel implements Parcelable {

    private String name;
    private String address;
    private String salonImage;
    private String salonId;
    private String website;
    private String phone;
    private String openHours;


    public SalonModel() {
    }

    protected SalonModel(Parcel in) {
        name = in.readString();
        address = in.readString();
        salonImage = in.readString();
        salonId = in.readString();
        website = in.readString();
        phone = in.readString();
        openHours = in.readString();
    }

    public static final Creator<SalonModel> CREATOR = new Creator<SalonModel>() {
        @Override
        public SalonModel createFromParcel(Parcel in) {
            return new SalonModel(in);
        }

        @Override
        public SalonModel[] newArray(int size) {
            return new SalonModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSalonImage() {
        return salonImage;
    }

    public void setSalonImage(String salonImage) {
        this.salonImage = salonImage;
    }

    public String getSalonId() {
        return salonId;
    }

    public void setSalonId(String salonId) {
        this.salonId = salonId;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenHours() {
        return openHours;
    }

    public void setOpenHours(String openHours) {
        this.openHours = openHours;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(salonImage);
        dest.writeString(salonId);
        dest.writeString(website);
        dest.writeString(phone);
        dest.writeString(openHours);
    }
}
