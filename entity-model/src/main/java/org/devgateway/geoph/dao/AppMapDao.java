package org.devgateway.geoph.dao;

import org.devgateway.geoph.model.AppMap;

import java.util.Date;

/**
 * @author dbianco
 *         created on sep 22 2016.
 */
public class AppMapDao {

    private Long id;

    private String name;

    private String description;

    private String key;

    private String type;

    private Date creationDate;

    private String base64preview;

    public AppMapDao(AppMap appMap){
        this.id = appMap.getId();
        this.name = appMap.getName();
        this.description = appMap.getDescription();
        this.key = appMap.getKey();
        this.type = appMap.getType();
        this.creationDate = appMap.getCreationDate();
        this.base64preview = appMap.getBase64preview();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getBase64preview() {
        return base64preview;
    }

    public void setBase64preview(String base64preview) {
        this.base64preview = base64preview;
    }
}
