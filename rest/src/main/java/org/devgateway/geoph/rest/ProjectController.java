package org.devgateway.geoph.rest;

import org.devgateway.geoph.core.request.AppRequestParams;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.request.ProjectOrder;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.core.services.ProjectService;
import org.devgateway.geoph.dao.ProjectMiniDao;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


/**
 * @author dbianco
 *         created on mar 08 2016.
 */
@RestController
@RequestMapping(value = "/projects")
public class ProjectController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterController.class);

    private final ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(method = GET)
    public Page<Project> findProjectsByParams(AppRequestParams filters,
                                              @PageableDefault(page = 0, size = 20, sort = "id") final Pageable pageable) {
        LOGGER.debug("findProjectsByParams");
        Parameters params = filters.getParameters();
        params.setPageable(pageable);
        return service.findProjectsByParams(params);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public Project findProjectById(@PathVariable final long id) {
        LOGGER.debug("findProjectById");
        return service.findById(id);
    }

    @RequestMapping(value = "/all", method = GET)
    public Page<ProjectMiniDao> findAllProjects(AppRequestParams filters,
                                         ProjectOrder projectOrder) {
        LOGGER.debug("findAllProjects");
        Parameters params = filters.getParameters();
        params.setProjectOrder(projectOrder);
        return service.findProjectMiniByParams(params);
    }


    @RequestMapping(value = "/count", method = GET)
    public StatsResponse countProjects(AppRequestParams filters) {
        LOGGER.debug("countProjects");
        return service.countProjectsByParams(filters.getParameters());
    }

    @RequestMapping(value = "/stats", method = GET)
     public Map<String, Map<String, Object>> projectStats(AppRequestParams filters) {
        LOGGER.debug("projectStats");
        Map<String, List<ProjectStatsResultsDao>> results = service.getStats(filters.getParameters());
        return createValueMap(results);
    }

    private Map<String, Map<String, Object>> createValueMap(Map<String, List<ProjectStatsResultsDao>> results) {
        Map<String, Map<String, Object>> ret = new HashMap<>();
        for(String level: results.keySet()){
            List<ProjectStatsResultsDao> statsList = results.get(level);
            for(ProjectStatsResultsDao stats : statsList) {
                Map<String, Object> typeMap = new HashMap<>();
                Map<String, Double> statusMap = new HashMap<>();
                statusMap.put(TransactionStatusEnum.getEnumById(stats.getStatusId()).getName(), stats.getTrxAmount());
                String type = TransactionTypeEnum.getEnumById(stats.getTypeId()).getName();
                typeMap.put(type, statusMap);
                if (ret.get(level) == null) {
                    ret.put(level, typeMap);
                    ret.get(level).put(type, statusMap);
                    ret.get(level).put("projectCount", stats.getProjectCount());
                } else {
                    if(ret.get(level).get(type) == null){
                        ret.get(level).put(type, statusMap);
                    } else {
                        ((Map)(ret.get(level)).get(type)).put(TransactionStatusEnum.getEnumById(stats.getStatusId()).getName(), stats.getTrxAmount());
                    }
                }
            }
        }
        return ret;
    }
}
