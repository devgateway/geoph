package org.devgateway.geoph.enums;

/**
 * @author dbianco
 *         created on abr 15 2016.
 */
public enum TransactionTypeEnum {

    COMMITMENTS(1), DISBURSEMENTS(2), EXPENDITURES(3);

    private final int id;

    TransactionTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static TransactionTypeEnum getEnumById(long id){
        for(TransactionTypeEnum e:TransactionTypeEnum.values()){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }

    public String getName(){
        return name().toLowerCase();
    }
}
