package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on abr 15 2016.
 */
public enum TransactionStatusEnum {

    TARGET(1), ACTUAL(2), CANCELLED(3);

    private final int id;

    TransactionStatusEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionStatusEnum getEnumById(long id){
        for(TransactionStatusEnum e:TransactionStatusEnum.values()){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }

}
