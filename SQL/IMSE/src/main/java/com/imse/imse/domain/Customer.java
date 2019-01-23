package com.imse.imse.domain;


import java.time.LocalDate;

public class Customer {

    private int customerId;

    private String customerName;

    private String customerSurname;

    private LocalDate customerSince;

    private int employeeId;

    public Customer(String name, String surname, int employee) {
        this.customerName = name;
        this.customerSurname = surname;
        this.customerSince = LocalDate.now();
        this.employeeId = employee;
    }

    /**
     * @return the customerId
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the customerSurname
     */
    public String getCustomerSurname() {
        return customerSurname;
    }

    /**
     * @param customerSurname the customerSurname to set
     */
    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    /**
     * @return the customerSince
     */
    public LocalDate getCustomerSince() {
        return customerSince;
    }

    /**
     * @param customerSince the customerSince to set
     */
    public void setCustomerSince(LocalDate customerSince) {
        this.customerSince = customerSince;
    }

    /**
     * @return the employeeId
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

}

