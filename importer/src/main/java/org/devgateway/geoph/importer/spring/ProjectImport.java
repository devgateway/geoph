package org.devgateway.geoph.importer.spring;

import org.devgateway.geoph.enums.FlowTypeEnum;
import org.devgateway.geoph.importer.processing.GeophProjectsImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author dbianco
 *         created on jul 01 2016.
 */

@Service
public class ProjectImport {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectImport.class);

    @Value("${import.type}")
    private String importType;

    @Autowired
    @Qualifier("grantImporter")
    private GeophProjectsImporter grantImporter;

    @Autowired
    @Qualifier("loanImporter")
    private GeophProjectsImporter loanImporter;

    @Autowired
    @Qualifier("pmcImporter")
    private GeophProjectsImporter pmcImporter;

    @Autowired
    private ConfigurableApplicationContext context;


    public void run(String... strings) throws Exception {
        LOGGER.info("......... Running import now .........");
        if(importType.toLowerCase().equals(FlowTypeEnum.GRANT.getLCName())){
            grantImporter.importProjects();
        } else if (importType.toLowerCase().equals(FlowTypeEnum.LOAN.getLCName())){
            loanImporter.importProjects();
        } else if (importType.toLowerCase().equals(FlowTypeEnum.PMC.getLCName())){
            pmcImporter.importProjects();
        }
        LOGGER.info("......... Import is done, please check the log .........");

        System.exit(SpringApplication.exit(context));
    }
}
