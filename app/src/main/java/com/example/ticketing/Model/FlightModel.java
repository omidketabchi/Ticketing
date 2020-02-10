package com.example.ticketing.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FlightModel implements Parcelable {

    private String id;
    private String source;
    private String destination;
    private String sourceAirport;
    private String destinationAirport;
    private String date;
    private String type;
    private String firstKind;
    private String secondKind;
    private String company;
    private String flightTime;
    private String landTime;
    private String capacity;
    private String flightId;
    private String priceYoung;
    private String priceChild;
    private String priceBaby;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSourceAirport() {
        return sourceAirport;
    }

    public void setSourceAirport(String sourceAirport) {
        this.sourceAirport = sourceAirport;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getLandTime() {
        return landTime;
    }

    public void setLandTime(String landTime) {
        this.landTime = landTime;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getPriceYoung() {
        return priceYoung;
    }

    public void setPriceYoung(String priceYoung) {
        this.priceYoung = priceYoung;
    }

    public String getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(String priceChild) {
        this.priceChild = priceChild;
    }

    public String getPriceBaby() {
        return priceBaby;
    }

    public void setPriceBaby(String priceBaby) {
        this.priceBaby = priceBaby;
    }

    public String getFirstKind() {
        return firstKind;
    }

    public void setFirstKind(String firstKind) {
        this.firstKind = firstKind;
    }

    public String getSecondKind() {
        return secondKind;
    }

    public void setSecondKind(String secondKind) {
        this.secondKind = secondKind;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.source);
        dest.writeString(this.destination);
        dest.writeString(this.sourceAirport);
        dest.writeString(this.destinationAirport);
        dest.writeString(this.date);
        dest.writeString(this.type);
        dest.writeString(this.firstKind);
        dest.writeString(this.secondKind);
        dest.writeString(this.company);
        dest.writeString(this.flightTime);
        dest.writeString(this.landTime);
        dest.writeString(this.capacity);
        dest.writeString(this.flightId);
        dest.writeString(this.priceYoung);
        dest.writeString(this.priceChild);
        dest.writeString(this.priceBaby);
    }

    public FlightModel() {
    }

    protected FlightModel(Parcel in) {
        this.id = in.readString();
        this.source = in.readString();
        this.destination = in.readString();
        this.sourceAirport = in.readString();
        this.destinationAirport = in.readString();
        this.date = in.readString();
        this.type = in.readString();
        this.firstKind = in.readString();
        this.secondKind = in.readString();
        this.company = in.readString();
        this.flightTime = in.readString();
        this.landTime = in.readString();
        this.capacity = in.readString();
        this.flightId = in.readString();
        this.priceYoung = in.readString();
        this.priceChild = in.readString();
        this.priceBaby = in.readString();
    }

    public static final Parcelable.Creator<FlightModel> CREATOR = new Parcelable.Creator<FlightModel>() {
        @Override
        public FlightModel createFromParcel(Parcel source) {
            return new FlightModel(source);
        }

        @Override
        public FlightModel[] newArray(int size) {
            return new FlightModel[size];
        }
    };
}
