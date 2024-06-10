package com.sarisi;

public class Customer {

    private String name;
    private String phoneNum;
    private String address;

    public Customer(String phoneNum, String name, String address) {
        this.phoneNum = phoneNum;
        this.name = name;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String customerName) {
        this.name = customerName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
