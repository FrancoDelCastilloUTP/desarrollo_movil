package com.dc.primer_avance;

public class BusStop {
    private String name;
    private String address;
    private String district;
    private String state;

    // Constructor

    public BusStop(String name, String address, String district, String state) {
        this.name = name;
        this.address = address;
        this.district = district;
        this.state = state;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getState() {
        return state;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setState(String state) {
        this.state = state;
    }
}