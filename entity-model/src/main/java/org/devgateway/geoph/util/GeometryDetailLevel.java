package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on mar 22 2016.
 */
public enum GeometryDetailLevel {
    LOW(0.05), MEDIUM(0.025), HIGH(0.01), ULTRA(0.005);

    private final double level;

    GeometryDetailLevel(double level){
        this.level=level;
    }

    public double getLevel() {
        return level;
    }
}
