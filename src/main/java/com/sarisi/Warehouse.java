package com.sarisi;

public class Warehouse {
    private String address;
    private String landline;
    private int capacity;
    private int space;
    private String manager;

    public Warehouse(String address, String landline, int capacity, int space, String manager) {
        this.address = address;
        this.landline = landline;
        this.capacity = capacity;
        this.space = space;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Warehouse) {
            Warehouse warehouse = (Warehouse) obj;
            return address.equalsIgnoreCase(warehouse.address);
        }
        return false;
    }
}
