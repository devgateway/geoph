package org.devgateway.geoph.enums;

/**
 * @author dbianco
 *         created on mar 22 2016.
 */
public enum GeometryDetail {
    LOW(0.05), MEDIUM(0.025), HIGH(0.01), ULTRA(0.005);

    private final double value;

    GeometryDetail(double value){
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
