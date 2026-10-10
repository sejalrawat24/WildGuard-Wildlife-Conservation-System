
package com.wildlife.wildlife_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProtectedArea {

    @JsonProperty("area_id")
    private long area_id;

    @JsonProperty("area_name")
    private String area_name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("area_size")
    private double area_size;

    @JsonProperty("description")
    private String description;

    public ProtectedArea() {
    }

    public ProtectedArea(long area_id, String area_name,
            String location, double area_size, String description) {
        this.area_id = area_id;
        this.area_name = area_name;
        this.location = location;
        this.area_size = area_size;
        this.description = description;
    }

    public long getArea_id() {
        return area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public String getLocation() {
        return location;
    }

    public double getArea_size() {
        return area_size;
    }

    public String getDescription() {
        return description;
    }

    public void setArea_id(long area_id) {
        this.area_id = area_id;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setArea_size(double area_size) {
        this.area_size = area_size;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
