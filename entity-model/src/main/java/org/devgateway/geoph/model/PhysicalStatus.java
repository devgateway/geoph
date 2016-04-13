package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on abr 06 2016.
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllPhysicalStatus",
                query = "from physical_status s"
        ),
        @NamedQuery(
                name = "findPhysicalStatusByCode",
                query = "from physical_status s where s.code = :code"
        ),
        @NamedQuery(
                name = "findPhysicalStatusById",
                query = "from physical_status s where s.id = :id"
        ),
        @NamedQuery(
                name = "findPhysicalStatusByName",
                query = "from physical_status s where s.name = :name"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity(name = "physical_status")
public class PhysicalStatus extends GenericPersistable implements Serializable {

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
