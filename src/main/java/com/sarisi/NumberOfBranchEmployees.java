package com.sarisi;

public class NumberOfBranchEmployees {
    private String branchAddress;
    private int numberOfEmployees;

    public NumberOfBranchEmployees(String branchAddress, int numberOfEmployees) {
        super();
        this.branchAddress = branchAddress;
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }
}
