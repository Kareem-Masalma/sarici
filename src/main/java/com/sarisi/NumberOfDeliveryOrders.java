package com.sarisi;

public class NumberOfDeliveryOrders {
    private String employee;
    private int numberOfOrders;

    public NumberOfDeliveryOrders(String employee, int numberOfOrders) {
        super();
        this.employee = employee;
        this.numberOfOrders = numberOfOrders;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
