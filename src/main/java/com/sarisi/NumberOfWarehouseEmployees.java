package com.sarisi;

public class NumberOfWarehouseEmployees {
    private String warehouseAddress;
    private int numberOfEmployees;

    public NumberOfWarehouseEmployees(String warehouseAddress, int numberOfEmployees) {
        super();
        this.warehouseAddress = warehouseAddress;
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

}
