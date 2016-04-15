package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on abr 15 2016.
 */
public enum TransactionTypeEnum {

    COMMITMENT(1), DISBURSEMENT(2), EXPENDITURE(3);

    private final int id;

    TransactionTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
