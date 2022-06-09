package com.sbitbd.couriermerchant.model;

public class trackModel {
    String riderID,lat,lon;
    public trackModel(String riderID, String lat, String lon) {
        this.riderID = riderID;
        this.lat = lat;
        this.lon = lon;
    }

    public trackModel() {
    }

    public String getRiderID() {
        return riderID;
    }

    public void setRiderID(String riderID) {
        this.riderID = riderID;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
