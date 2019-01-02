package com.imse.imse.Payloads;

public class EmployeeUpdatePayload {

    private String whatToUpdate;

    private String whereToUpdate;


    public EmployeeUpdatePayload(String whatToUpdate, String whereToUpdate) {
        this.whatToUpdate = whatToUpdate;
        this.whereToUpdate = whereToUpdate;
    }

    public String getWhatToUpdate() {
        return whatToUpdate;
    }

    public String getWhereToUpdate() {
        return whereToUpdate;
    }
}
