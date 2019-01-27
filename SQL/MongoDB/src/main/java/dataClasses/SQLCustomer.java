package dataClasses;


import java.sql.Date;
import java.time.LocalDate;

public class SQLCustomer {

    private int CustomerId;

    private String CustomerName;

    private String CustomerSurname;

    private LocalDate CustomerSince;

    private int EmployeeId;

    public SQLCustomer() {
        //TODO
    }

    public SQLCustomer(String name, String surname, int Employee) {
        this.CustomerName = name;
        this.CustomerSurname = surname;
        this.CustomerSince = LocalDate.now();
        this.EmployeeId = Employee;
    }

    public SQLCustomer(int CustomerId, String name, String surname, LocalDate CustomerSince, int Employee) {
        this.CustomerId = CustomerId;
        this.CustomerName = name;
        this.CustomerSurname = surname;
        this.CustomerSince = CustomerSince;
        this.EmployeeId = Employee;
    }



    /**
     * @return the CustomerId
     */
    public int getCustomerId() {
        return CustomerId;
    }

    /**
     * @param CustomerId the CustomerId to set
     */
    public void setCustomerId(int CustomerId) {
        this.CustomerId = CustomerId;
    }

    /**
     * @return the CustomerName
     */
    public String getCustomerName() {
        return CustomerName;
    }

    /**
     * @param CustomerName the CustomerName to set
     */
    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    /**
     * @return the CustomerSurname
     */
    public String getCustomerSurname() {
        return CustomerSurname;
    }

    /**
     * @param CustomerSurname the CustomerSurname to set
     */
    public void setCustomerSurname(String CustomerSurname) {
        this.CustomerSurname = CustomerSurname;
    }

    /**
     * @return the CustomerSince
     */
    public LocalDate getCustomerSince() {
        return CustomerSince;
    }

    /**
     * @param CustomerSince the CustomerSince to set
     */
    public void setCustomerSince(LocalDate CustomerSince) {
        this.CustomerSince = CustomerSince;
    }

    /**
     * @return the EmployeeId
     */
    public int getEmployeeId() {
        return EmployeeId;
    }

    /**
     * @param EmployeeId the EmployeeId to set
     */
    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

}

