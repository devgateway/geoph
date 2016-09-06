package org.devgateway.geoph.enums;

/**
 * @author dbianco
 *         created on mar 14 2016.
 */
public enum LocationAdmLevelEnum {
    REGION(1), PROVINCE(2), MUNICIPALITY(3);

    private final int level;

    LocationAdmLevelEnum(int level){
        this.level=level;
    }

    public int getLevel() {
        return level;
    }

    public static LocationAdmLevelEnum getEnumByLevel(long level){
        for(LocationAdmLevelEnum e:LocationAdmLevelEnum.values()){
            if(e.getLevel()==level){
                return e;
            }
        }
        return null;
    }


    public static LocationAdmLevelEnum getEnumByName(String name){
        for(LocationAdmLevelEnum e:LocationAdmLevelEnum.values()){
            if(e.name().equalsIgnoreCase(name)){
                return e;
            }
        }
        return null;
    }
}
