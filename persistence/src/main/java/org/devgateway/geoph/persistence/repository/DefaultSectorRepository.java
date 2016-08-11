package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.SectorRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.SectorResultsDao;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.Project_;
import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dbianco
 *         created on mar 18 2016.
 */
@Service
public class DefaultSectorRepository implements SectorRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<Sector> findAll() {
        return em.createNamedQuery("findAllSectors", Sector.class)
                .getResultList();
    }

    @Override
    public Sector findById(Long id) {
        return em.createNamedQuery("findSectorById", Sector.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Sector findByCode(String code) {
        return em.createNamedQuery("findSectorsByCode", Sector.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public List<Sector> findByLevel(int level) {
        return sectorInitializer(em.createNamedQuery("findSectorsByLevel", Sector.class)
                .setParameter("level", level)
                .getResultList());
    }

    @Override
    public List<SectorResultsDao> findFundingBySector(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SectorResultsDao> criteriaQuery = criteriaBuilder.createQuery(SectorResultsDao.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
        multiSelect.add(sectorJoin);
        multiSelect.add(projectRoot);
        groupByList.add(sectorJoin);
        groupByList.add(projectRoot);

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<SectorResultsDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    private List<Sector> sectorInitializer(List<Sector> sectors) {
        for (Sector sector : sectors) {
            if (sector.getItems() != null) {
                sectorInitializer(sector.getItems());
            }
        }
        return sectors;
    }
}
