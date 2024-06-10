package com.sarisi;

public class BranchEmployee extends Employee {

    private String branchAddress;
    private double salary;

    public BranchEmployee(int ssn, String name, int age, String address, String phone, int supervisor, String hiringDate) {
        super(ssn, name, age, address, phone, supervisor, hiringDate);
    }

    public BranchEmployee(int ssn, String name, int age, String address, String phone, int supervisor, String hiringDate,
                          String branchAddress, double salary) {
        super(ssn, name, age, address, phone, supervisor, hiringDate);
        this.branchAddress = branchAddress;
        this.salary = salary;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
