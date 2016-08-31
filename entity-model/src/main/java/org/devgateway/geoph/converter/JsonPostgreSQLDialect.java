package org.devgateway.geoph.converter;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.spatial.dialect.postgis.PostgisDialect;

import java.sql.Types;

/**
 * @author dbianco
 *         created on jun 28 2016.
 */

    public class JsonPostgreSQLDialect extends PostgisDialect {

    public JsonPostgreSQLDialect() {
        super();
        this.registerColumnType(Types.JAVA_OBJECT, "json");
    }
}