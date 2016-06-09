/*******************************************************************************
 * Copyright (c) 2015 Development Gateway, Inc and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the MIT License (MIT)
 * which accompanies this distribution, and is available at
 * https://opensource.org/licenses/MIT
 *
 * Contributors:
 * Development Gateway - initial API and implementation
 *******************************************************************************/
/**
 *
 */
package org.devgateway.geoph.services.spring;

import java.sql.Connection;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.postgresql.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * @author mpostelnicu
 *
 */
@Configuration
@EnableJpaAuditing
@PropertySource("classpath:/org/devgateway/geoph/services/application.properties")
public class DatabaseConfiguration {

    protected static Logger logger = Logger.getLogger(DatabaseConfiguration.class);

    @Value("#{environment['database.url']}")
    private String databaseUrl;

    @Value("#{environment['database.username']}")
    private String databaseUsername;

    @Value("#{environment['database.password']}")
    private String databasePassword;

    /**
     * This bean creates the JNDI tree and registers the
     * {@link javax.sql.DataSource} to this tree. This allows Pentaho Classic
     * Engine to use a {@link javax.sql.DataSource} ,in our case backed by a
     * connection pool instead of always opening up JDBC connections. Should
     * significantly improve performance of all classic reports. In PRD use
     * connection type=JNDI and name toolkitDS. To use it in PRD you need to add the
     * configuration to the local PRD. Edit ~/.pentaho/simple-jndi/default.properties
     * and add the following:
     * toolkitDS/type=javax.sql.DataSource
     * toolkitDS/driver=org.apache.derby.jdbc.ClientDriver
     * toolkitDS/user=app
     * toolkitDS/password=app
     * toolkitDS/url=jdbc:derby://localhost//derby/toolkit
     *
     * @return
     */
    @Bean
    public SimpleNamingContextBuilder jndiBuilder() {
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.bind("toolkitDS", dataSource());
        try {
            builder.activate();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return builder;
    }

    /**
     * Creates a {@link javax.sql.DataSource} based on Tomcat {@link DataSource}
     * @return
     */
    @Bean
    @DependsOn(value = {"mbeanServer"})
    public DataSource dataSource() {
        PoolProperties pp=new PoolProperties();
        pp.setJmxEnabled(false);
        pp.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        pp.setInitialSize(20);
        pp.setMaxWait(60000);

        DataSource dataSource = new DataSource(pp);

        dataSource.setDriverClassName(Driver.class.getName());
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        return dataSource;
    }





}
