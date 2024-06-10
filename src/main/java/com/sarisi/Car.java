package com.sarisi;

public class Car {

    private String plateNumber;
    private String purchaseDate;
    private String warehouse;

    public Car(String plateNumber, String purchaseDate, String warehouse) {
        super();
        this.plateNumber = plateNumber;
        this.purchaseDate = purchaseDate;
        this.warehouse = warehouse;
    }

    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Car) {
            Car car = (Car) obj;
            return plateNumber.equalsIgnoreCase(car.plateNumber);
        }
        return false;
    }
}
