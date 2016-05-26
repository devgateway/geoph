package org.devgateway.geoph.model;

import java.io.Serializable;

/**
 * @author dbianco
 *         created on may 24 2016.
 */
public class IndicatorDetailId  implements Serializable {

    private long indicatorId;

    private long locationId;

    public long getIndicatorId() {
        return indicatorId;
    }

    public void setIndicatorId(long indicatorId) {
        this.indicatorId = indicatorId;
    }
}
