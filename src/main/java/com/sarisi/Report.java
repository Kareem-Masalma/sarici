package com.sarisi;

public class Report {
    private int orders;
    private double ordersIncome;
    private int employees;
    private int customers;
    private int cars;
    private int supplied;

    public Report(int orders, double ordersIncome, int employees, int customers, int cars, int supplied) {
        this.orders = orders;
        this.ordersIncome = ordersIncome;
        this.employees = employees;
        this.customers = customers;
        this.cars = cars;
        this.supplied = supplied;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getEmployees() {
        return employees;
    }

    public void setEmployees(int employees) {
        this.employees = employees;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public int getCars() {
        return cars;
    }

    public void setCars(int cars) {
        this.cars = cars;
    }

    public int getSupplied() {
        return supplied;
    }

    public void setSupplied(int supplied) {
        this.supplied = supplied;
    }

    public double getOrdersIncome() {
        return ordersIncome;
    }

    public void setOrdersIncome(double ordersIncome) {
        this.ordersIncome = ordersIncome;
    }
}
