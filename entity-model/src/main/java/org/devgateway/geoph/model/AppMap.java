package org.devgateway.geoph.model;

import org.devgateway.geoph.converter.StringJsonUserType;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@TypeDefs( {@TypeDef( name= "StringJsonObject", typeClass = StringJsonUserType.class)})
public class AppMap extends GenericPersistable implements Serializable {

    private String name;

    private String description;

    private String key;

    @Type(type = "StringJsonObject")
    private String jsonAppMap;

    private Date creationDate;

    public AppMap(){

    }

    public AppMap(String name, String description, String jsonAppMap, String key) {
        this.name = name;
        this.description = description;
        this.jsonAppMap = jsonAppMap;
        this.creationDate = new Date();
        this.setKey(key);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJsonAppMap() {
        return jsonAppMap;
    }

    public void setJsonAppMap(String jsonAppMap) {
        this.jsonAppMap = jsonAppMap;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
