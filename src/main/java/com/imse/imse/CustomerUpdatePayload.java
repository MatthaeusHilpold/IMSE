package com.imse.imse;

public class CustomerUpdatePayload {

    private String whatToUpdate;

    private String whereToUpdate;


    public CustomerUpdatePayload(String whatToUpdate, String whereToUpdate) {
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