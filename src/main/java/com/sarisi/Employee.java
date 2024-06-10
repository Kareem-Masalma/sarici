package com.sarisi;

public class Employee {

    private int ssn;
    private String name;
    private int age;
    private String address;
    private String phone;
    private int supervisor;
    private String hiringDate;

    public Employee() {
    }

    public Employee(int ssn) {
        this.ssn = ssn;
    }

    public Employee(int ssn, String name, int age, String address, String phone, int supervisor, String hiringDate) {
        this.ssn = ssn;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
        this.supervisor = supervisor;
        this.hiringDate = hiringDate;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(int supervisor) {
        this.supervisor = supervisor;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            return this.ssn == employee.ssn;
        }
        return false;
    }
}

