package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.SectorRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.SectorResultsDao;
import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable("findSectorById")
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
    @Cacheable("findSectorByParams")
    public List<SectorResultsDao> findFundingBySector(Parameters params, int trxType, int trxStatus) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SectorResultsDao> criteriaQuery = criteriaBuilder.createQuery(SectorResultsDao.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, ProjectSector> sectorJoin = projectRoot.join(Project_.sectors);
        Join<ProjectSector, ProjectSectorId> projectSectorIdJoin = sectorJoin.join(ProjectSector_.pk);
        Join<Project, Transaction> transactionJoin = projectRoot.join(Project_.transactions);
        multiSelect.add(projectSectorIdJoin.get(ProjectSectorId_.sector));
        if(params.getLocations()==null) {
            multiSelect.add(criteriaBuilder.sum(criteriaBuilder.prod(transactionJoin.get(Transaction_.amount), sectorJoin.get(ProjectSector_.utilization))));
            FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);
        } else {
            FilterHelper.filterProjectQueryForSectors(params, criteriaBuilder, projectRoot, predicates, multiSelect, sectorJoin, transactionJoin);
        }

        multiSelect.add(criteriaBuilder.count(projectRoot.get(Project_.id)));
        groupByList.add(projectSectorIdJoin.get(ProjectSectorId_.sector));

        predicates.add(transactionJoin.get(Transaction_.transactionTypeId).in(trxType));
        predicates.add(transactionJoin.get(Transaction_.transactionStatusId).in(trxStatus));
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
