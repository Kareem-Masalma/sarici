package com.sarisi;

public class Order {
    private int orderId;
    private String employee;
    private String car;
    private double price;
    private String address;
    private String orderDate;
    private String customerName;

    public Order(int orderId, String employee, String car, double price, String address, String orderDate, String customerName) {
        super();
        this.orderId = orderId;
        this.employee = employee;
        this.car = car;
        this.price = price;
        this.address = address;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
