package dataClasses;

import java.time.LocalDate;

public class Spouse {

    private int customerId;

    private String spouseName;

    private byte hasChildren;

    private LocalDate spouseSince;

    public Spouse(String name, byte hasChildren) {
        this.spouseName = name;
        this.hasChildren = hasChildren;
        this.spouseSince = LocalDate.now();
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
     * @return the spouseName
     */
    public String getSpouseName() {
        return spouseName;
    }

    /**
     * @param spouseName the spouseName to set
     */
    public void setSpouseName(String spouseName) {
        this.spouseName = spouseName;
    }

    /**
     * @return the hasChildren
     */
    public byte getHasChildren() {
        return hasChildren;
    }

    /**
     * @param hasChildren the hasChildren to set
     */
    public void setHasChildren(byte hasChildren) {
        this.hasChildren = hasChildren;
    }

    /**
     * @return the spouseSince
     */
    public LocalDate getSpouseSince() {
        return spouseSince;
    }

    /**
     * @param spouseSince the spouseSince to set
     */
    public void setSpouseSince(LocalDate spouseSince) {
        this.spouseSince = spouseSince;
    }

}

