package com.sarisi;

public class Branch {
    private String address;
    private String landline;
    private String manager;

    public Branch(String address, String landline, String manager) {
        super();
        this.address = address;
        this.landline = landline;
        this.manager = manager;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandline() {
        return landline;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Branch) {
            Branch branch = (Branch) obj;
            return address.equalsIgnoreCase(branch.address);
        }
        return false;
    }
}
