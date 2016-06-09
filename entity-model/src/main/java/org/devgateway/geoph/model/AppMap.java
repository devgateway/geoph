package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * @author dbianco
 *         created on abr 20 2016.
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class AppMap extends GenericPersistable implements Serializable {

    public static final String ALPHABET = "BCDFGHIJKLMNPQRSTVWXZ";
    public static final int ALPHABET_NUMBER = ALPHABET.length();

    private String name;

    private String description;

    private String key;

    private String jsonAppMap;

    private Date creationDate;

    public AppMap(){

    }

    public AppMap(String name, String description, String jsonAppMap) {
        this.name = name;
        this.description = description;
        this.jsonAppMap = jsonAppMap;
        this.creationDate = new Date();
        this.setKey(getRandomKey()+getRandomKey());
    }

    private static String getRandomKey(){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(ALPHABET.charAt(r.nextInt(ALPHABET_NUMBER)));
        }
        return sb.toString();
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
