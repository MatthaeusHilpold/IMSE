package com.imse.imse.domain;

import java.time.LocalDate;

public class Employee {

    private int employeeId;

    private String telephoneNumber;

    private String name;

    private String surname;

    private int baseSalary;

    private LocalDate employeeSince;

    private int SupervisorId;

    public String getCompanyUIDNumber() {
        return companyUIDNumber;
    }

    private String companyUIDNumber;

    public Employee(String telephoneNumber, String name, String surname, int baseSalary, int supervisorId, String uidNumber ) {
        this.telephoneNumber = telephoneNumber;
        this.name = name;
        this.surname = surname;
        this.baseSalary = baseSalary;
        this.employeeSince =  LocalDate.now();
        this.SupervisorId = supervisorId;
        this.companyUIDNumber = uidNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public LocalDate getEmployeeSince() {
        return employeeSince;
    }

    public int getSupervisorId() {
        return SupervisorId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public void setEmployeeSince(LocalDate employeeSince) {
        this.employeeSince = employeeSince;
    }

    public void setSupervisorId(int supervisorId) {
        SupervisorId = supervisorId;
    }
}
