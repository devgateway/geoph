package org.devgateway.geoph.enums;

/**
 * @author dbianco
 *         created on ago 05 2016.
 */
public enum AppMapTypeEnum {

    SAVE(1), SHARE(2), PRINT(3);

    private final int id;

    AppMapTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static AppMapTypeEnum getEnumById(long id){
        for(AppMapTypeEnum e:AppMapTypeEnum.values()){
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
