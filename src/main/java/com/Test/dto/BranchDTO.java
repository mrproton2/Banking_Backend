package com.Test.dto;

public class BranchDTO {
    private int id;
    private String branchName;
    private String city;
    private String state;
    private String pincode;
    private String branchAddress;

    public BranchDTO(int id,String branchName, String city, String state, String pincode, String branchAddress) {
        this.id=id;
        this.branchName = branchName;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.branchAddress = branchAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() { return branchName; }
    public void setBranchName(String branchName) { this.branchName = branchName; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public String getBranchAddress() { return branchAddress; }
    public void setBranchAddress(String branchAddress) { this.branchAddress = branchAddress; }
}
