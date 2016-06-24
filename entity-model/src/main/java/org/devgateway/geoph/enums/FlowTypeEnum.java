package org.devgateway.geoph.enums;

/**
 * @author dbianco
 *         created on mar 30 2016.
 */
public enum FlowTypeEnum {
    LOAN(1), GRANT(2), PMC(3);

    private final int id;

    FlowTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static FlowTypeEnum getEnumById(long id){
        for(FlowTypeEnum e:FlowTypeEnum.values()){
            if(e.getId()==id){
                return e;
            }
        }
        return null;
    }
}
