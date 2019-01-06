package com.imse.imse;

public class SpouseUpdatePayload {

    private String whatToUpdate;

    private String whereToUpdate;


    public SpouseUpdatePayload(String whatToUpdate, String whereToUpdate) {
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