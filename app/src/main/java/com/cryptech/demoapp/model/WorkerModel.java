package com.cryptech.demoapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WorkerModel implements Parcelable {

    private String name;
    private String username;
    private String password;
    private String workerId;

    protected WorkerModel(Parcel in) {
        name = in.readString();
        username = in.readString();
        password = in.readString();
        workerId = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readLong();
        }
    }

    public static final Creator<WorkerModel> CREATOR = new Creator<WorkerModel>() {
        @Override
        public WorkerModel createFromParcel(Parcel in) {
            return new WorkerModel(in);
        }

        @Override
        public WorkerModel[] newArray(int size) {
            return new WorkerModel[size];
        }
    };

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }

    private Long rating;

    public WorkerModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(workerId);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(rating);
        }
    }
}
