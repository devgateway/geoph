package org.devgateway.geoph.services.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.services.util.FilterHelper;
import org.devgateway.geoph.util.Parameters;
import org.devgateway.geoph.util.TransactionStatusEnum;
import org.devgateway.geoph.util.TransactionTypeEnum;
import org.devgateway.geoph.util.queries.SectorResultsQueryHelper;
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
    public List<SectorResultsQueryHelper> findFundingBySector(Parameters params, int trxTypeId, int trxStatusId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<SectorResultsQueryHelper> criteriaQuery = criteriaBuilder.createQuery(SectorResultsQueryHelper.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
        multiSelect.add(sectorJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot));
        groupByList.add(sectorJoin);

        if(trxTypeId!=0 && trxStatusId!=0) {
            FilterHelper.addTransactionJoin(criteriaBuilder, multiSelect, projectRoot, trxTypeId, trxStatusId);
        }

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<SectorResultsQueryHelper> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }

    private List<Sector> sectorInitializer(List<Sector> sectors){
        for(Sector sector:sectors){
            if(sector.getItems()!=null){
                sectorInitializer(sector.getItems());
            }
        }
        return sectors;
    }
}
