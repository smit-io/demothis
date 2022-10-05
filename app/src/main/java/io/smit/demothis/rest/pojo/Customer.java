package io.smit.demothis.rest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.smit.holdme.rest.constants.SerializedNames;

public class Customer {
    @Expose
    @SerializedName(SerializedNames.CUSTOMER_NAME)
    private String name;
    @Expose
    @SerializedName(SerializedNames.CUSTOMER_LAT)
    private double latitude;
    @Expose
    @SerializedName(SerializedNames.CUSTOMER_LONG)
    private double longitude;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
