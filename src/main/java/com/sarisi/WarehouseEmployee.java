package com.sarisi;

public class WarehouseEmployee extends Employee {
    private String warehouseAddress;
    private double salary;

    public WarehouseEmployee(int ssn, String name, int age, String address, String phone, int supervisor,
                             String hiringDate) {
        super(ssn, name, age, address, phone, supervisor, hiringDate);
    }

    public WarehouseEmployee(int ssn, String name, int age, String address, String phone, int supervisor, String hiringDate,
                             String warehouseAddress, double salary) {
        super(ssn, name, age, address, phone, supervisor, hiringDate);
        this.warehouseAddress = warehouseAddress;
        this.salary = salary;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
