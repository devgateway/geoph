package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.PhysicalStatusRepository;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.PhysicalStatusDao;
import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Project;
import org.devgateway.geoph.model.Project_;
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
 *         created on abr 12 2016.
 */
@Service
public class DefaultPhysicalStatusRepository implements PhysicalStatusRepository {

    @Autowired
    EntityManager em;

    @Override
    public List<PhysicalStatus> findAll() {
        return em.createNamedQuery("findAllPhysicalStatus", PhysicalStatus.class)
                .getResultList();
    }

    @Override
    public PhysicalStatus findByName(String name) {
        return em.createNamedQuery("findPhysicalStatusByName", PhysicalStatus.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public PhysicalStatus findByCode(String code) {
        return em.createNamedQuery("findPhysicalStatusByCode", PhysicalStatus.class)
                .setParameter("code", code)
                .getSingleResult();
    }

    @Override
    public List<PhysicalStatusDao> findFundingByPhysicalStatus(Parameters params) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<PhysicalStatusDao> criteriaQuery = criteriaBuilder.createQuery(PhysicalStatusDao.class);

        Root<Project> projectRoot = criteriaQuery.from(Project.class);

        List<Selection<?>> multiSelect = new ArrayList<>();
        List<Predicate> predicates = new ArrayList();
        List<Expression<?>> groupByList = new ArrayList<>();

        Join<Project, PhysicalStatus> physicalStatusJoin = projectRoot.join(Project_.physicalStatus);
        multiSelect.add(physicalStatusJoin);
        multiSelect.add(projectRoot);
        groupByList.add(physicalStatusJoin);
        groupByList.add(projectRoot);

        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        criteriaQuery.where(other);


        criteriaQuery.groupBy(groupByList);
        TypedQuery<PhysicalStatusDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));

        return query.getResultList();
    }
}
