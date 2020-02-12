package com.example.ticketing.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ChairModel implements Parcelable {

    private String chair;
    private String status;

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.chair);
        dest.writeString(this.status);
    }

    public ChairModel() {
    }

    protected ChairModel(Parcel in) {
        this.chair = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<ChairModel> CREATOR = new Parcelable.Creator<ChairModel>() {
        @Override
        public ChairModel createFromParcel(Parcel source) {
            return new ChairModel(source);
        }

        @Override
        public ChairModel[] newArray(int size) {
            return new ChairModel[size];
        }
    };
}
