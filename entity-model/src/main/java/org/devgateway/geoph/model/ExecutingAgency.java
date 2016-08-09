package org.devgateway.geoph.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

/**
 * @author dbianco
 *         created on may 05 2016.
 */
@NamedQueries({
        @NamedQuery(
                name = "findAllExecutingAgency",
                query = "from ExecutingAgency s"
        )
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@DiscriminatorValue(value="executing_agency")
public class ExecutingAgency extends Agency implements Serializable {


}
