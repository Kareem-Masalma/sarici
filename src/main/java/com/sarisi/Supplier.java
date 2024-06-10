package com.sarisi;

public class Supplier {

    private int ssn;
    private String name;
    private String company;
    private String address;
    private String phone;

    public Supplier(int ssn, String supplierName, String company, String address, String phone) {
        this.ssn = ssn;
        this.name = supplierName;
        this.company = company;
        this.address = address;
        this.phone = phone;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Supplier) {
            Supplier supplier = (Supplier) obj;
            return ssn == supplier.ssn;
        }
        return false;
    }
}
