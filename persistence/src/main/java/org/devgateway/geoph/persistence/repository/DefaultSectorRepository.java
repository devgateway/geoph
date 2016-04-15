package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.*;
import org.devgateway.geoph.persistence.util.FilterHelper;
import org.devgateway.geoph.util.FlowTypeEnum;
import org.devgateway.geoph.util.Parameters;
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
    public List<Object> findFundingBySector(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, Sector> sectorJoin = projectRoot.join(Project_.sectors);
        multiSelect.add(sectorJoin);
        multiSelect.add(criteriaBuilder.countDistinct(projectRoot).alias("projectCount"));
        groupByList.add(sectorJoin);

        Join<Project, Transaction> transaction0Join = projectRoot.join(Project_.transactions);
        multiSelect.add(criteriaBuilder.countDistinct(transaction0Join).alias("transactionCount"));

        Join<Project, Transaction> transaction1Join = projectRoot.join(Project_.transactions, JoinType.LEFT);
        transaction1Join.on(transaction1Join.get(Transaction_.flowType).in(FlowTypeEnum.LOAN.name().toLowerCase()));
        multiSelect.add(criteriaBuilder.sum(transaction1Join.get(Transaction_.amount)).alias("loans"));

        Join<Project, Transaction> transaction2Join = projectRoot.join(Project_.transactions, JoinType.LEFT);
        transaction2Join.on(transaction2Join.get(Transaction_.flowType).in(FlowTypeEnum.GRANT.name().toLowerCase()));
        multiSelect.add(criteriaBuilder.sum(transaction2Join.get(Transaction_.amount)).alias("grants"));

        Join<Project, Transaction> transaction3Join = projectRoot.join(Project_.transactions, JoinType.LEFT);
        transaction3Join.on(transaction3Join.get(Transaction_.flowType).in(FlowTypeEnum.PMC.name().toLowerCase()));
        multiSelect.add(criteriaBuilder.sum(transaction3Join.get(Transaction_.amount)).alias("pmcs"));

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<Object> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

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
