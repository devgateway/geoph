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

    @Type(type = "text")
    private String description;

    private String key;

    private String compareMapKey;

    private String md5;

    private String type;

    @Type(type = "StringJsonObject")
    private String jsonAppMap;

    private Date creationDate;

    @Type(type = "text")
    private String base64preview;

    public AppMap(){

    }

    public String getBase64preview() {
        return base64preview;
    }

    public void setBase64preview(String base64preview) {
        this.base64preview = base64preview;
    }

    public AppMap(String name, String description, String jsonAppMap, String key, String compareMapKey,
                  String md5, String type, String base64preview) {
        this.name = name;
        this.description = description;
        this.jsonAppMap = jsonAppMap;
        this.creationDate = new Date();
        this.key = key;
        this.compareMapKey = compareMapKey;
        this.md5 = md5;
        this.type = type;
        this.base64preview=base64preview;
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

    public String getCompareMapKey() {
        return compareMapKey;
    }

    public void setCompareMapKey(final String compareMapKey) {
        this.compareMapKey = compareMapKey;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
