package com.sarisi;

public class Delivery extends Employee {
    private double salary;

    public Delivery(int ssn, String name, int age, String address, String phone, int supervisor, String hiringDate) {
        super(ssn, name, age, address, phone, supervisor, hiringDate);
    }

    public Delivery(int ssn, String name, int age, String address, String phone, int supervisor, String hiringDate,
                    double salary) {
        super(ssn, name, age, address, phone, supervisor, hiringDate);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
