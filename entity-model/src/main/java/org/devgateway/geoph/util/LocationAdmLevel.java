package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on mar 14 2016.
 */
public enum LocationAdmLevel {
    REGION(1), PROVINCE(2), MUNICIPALITY(3);

    private final int level;

    LocationAdmLevel(int level){
        this.level=level;
    }

    public int getLevel() {
        return level;
    }
}
