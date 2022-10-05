package io.smit.demothis.rest.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import io.smit.demothis.rest.constants.SerializedNames;


public class Hub {
    @Expose
    @SerializedName(SerializedNames.HUB_NAME)
    private String name;
    @Expose
    @SerializedName(SerializedNames.HUB_OPENTIME)
    private LocalDateTime openingTime;
    @Expose
    @SerializedName(SerializedNames.HUB_CLOSETIME)
    private LocalDateTime closingTime;
    @Expose
    @SerializedName(SerializedNames.HUB_LAT)
    private double latitude;
    @Expose
    @SerializedName(SerializedNames.HUB_LONG)
    private double longitude;
    @Expose
    @SerializedName(SerializedNames.HUB_ID)
    private int id;
    private int manhattanDistance;


    public Hub(String name) {
        this.name = name;
    }

    public Hub(int id, String name, LocalDateTime openingTime, LocalDateTime closingTime, double latitude, double longitude) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getManhattanDistance() {
        return manhattanDistance;
    }

    public void setManhattanDistance(int manhattanDistance) {
        this.manhattanDistance = manhattanDistance;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
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

    @Override
    public String toString() {
        return "Hub{" +
                "name='" + name + '\'' +
                ", openingTime=" + openingTime +
                ", closingTime=" + closingTime +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", id=" + id +
                ", manhattanDistance=" + manhattanDistance +
                '}';
    }
}
