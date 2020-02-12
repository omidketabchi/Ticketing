package com.example.ticketing.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class BusModel implements Parcelable {

    private String id;
    private String ticketId;
    private String source;
    private String destination;
    private String sourceTerminal;
    private String destinationTerminal;
    private String date;
    private String time;
    private String type;
    private String distance;
    private String capacity;
    private String price;
    private List<ChairModel> chairModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSourceTerminal() {
        return sourceTerminal;
    }

    public void setSourceTerminal(String sourceTerminal) {
        this.sourceTerminal = sourceTerminal;
    }

    public String getDestinationTerminal() {
        return destinationTerminal;
    }

    public void setDestinationTerminal(String destinationTerminal) {
        this.destinationTerminal = destinationTerminal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ChairModel> getChairModel() {
        return chairModel;
    }

    public void setChairModel(List<ChairModel> chairModel) {
        this.chairModel = chairModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.ticketId);
        dest.writeString(this.source);
        dest.writeString(this.destination);
        dest.writeString(this.sourceTerminal);
        dest.writeString(this.destinationTerminal);
        dest.writeString(this.date);
        dest.writeString(this.time);
        dest.writeString(this.type);
        dest.writeString(this.distance);
        dest.writeString(this.capacity);
        dest.writeString(this.price);
//        dest.writeList(this.chairModel);
    }

    public BusModel() {
    }

    protected BusModel(Parcel in) {
        this.id = in.readString();
        this.ticketId = in.readString();
        this.source = in.readString();
        this.destination = in.readString();
        this.sourceTerminal = in.readString();
        this.destinationTerminal = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.type = in.readString();
        this.distance = in.readString();
        this.capacity = in.readString();
        this.price = in.readString();
//        this.chairModel = new ArrayList<ChairModel>();
//        in.readList(this.chairModel, ChairModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<BusModel> CREATOR = new Parcelable.Creator<BusModel>() {
        @Override
        public BusModel createFromParcel(Parcel source) {
            return new BusModel(source);
        }

        @Override
        public BusModel[] newArray(int size) {
            return new BusModel[size];
        }
    };
}
