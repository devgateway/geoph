package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.ProjectRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.core.response.StatsResponse;
import org.devgateway.geoph.core.services.ProjectService;
import org.devgateway.geoph.dao.ProjectMiniDao;
import org.devgateway.geoph.dao.ProjectMiniSummaryDao;
import org.devgateway.geoph.dao.ProjectPageDao;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;
import org.devgateway.geoph.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author dbianco
 *         created on mar 08 2016.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjectServiceImpl.class);

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public ProjectPageDao findById(long id) {
        return new ProjectPageDao(projectRepository.findById(id));
    }

    @Override
    public Page<ProjectMiniSummaryDao> findProjectsByParams(Parameters params) {
        List<ProjectMiniDao> projectList = projectRepository.findProjectsByParams(params);
        List<ProjectMiniSummaryDao> summaryMap = new ArrayList<>();
        if(projectList!=null && projectList.size()>0){
            ProjectMiniDao first = projectList.iterator().next();
            ProjectMiniSummaryDao current = new ProjectMiniSummaryDao(first);
            for(ProjectMiniDao project :  projectList){
                if(!current.getId().equals(project.getId())){
                    summaryMap.add(current);
                    current = new ProjectMiniSummaryDao(project);
                }
                current.addTrxAmount(project);
            }
            summaryMap.add(current);
        }

        Page<ProjectMiniSummaryDao> ret;
        if(params.getPageable()!=null) {
            ret = new PageImpl<ProjectMiniSummaryDao>(summaryMap.subList(params.getPageable().getOffset() * params.getPageable().getPageSize(), params.getPageable().getOffset() * params.getPageable().getPageSize() + params.getPageable().getPageSize()), params.getPageable(), summaryMap.size());
        } else {
            ret = new PageImpl<ProjectMiniSummaryDao>(summaryMap);
        }
        return ret;
    }

    @Override
    public Page<ProjectMiniDao> findProjectMiniByParams(Parameters params) {
        return projectRepository.findProjectMiniByParams(params);
    }

    @Override
    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public StatsResponse countProjectsByParams(Parameters params) {
        return projectRepository.countProjectsByParams(params);
    }

    @Override
    public Map<String, List<ProjectStatsResultsDao>> getStats(Parameters params) {
        return projectRepository.getStats(params);
    }
}
