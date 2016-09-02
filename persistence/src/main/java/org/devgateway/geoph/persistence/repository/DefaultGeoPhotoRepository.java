package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.core.repositories.GeoPhotoRepositoryCustom;
import org.devgateway.geoph.core.request.Parameters;
import org.devgateway.geoph.dao.GeoPhotoDao;
import org.devgateway.geoph.model.GeoPhoto;
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
 *         created on abr 29 2016.
 */
@Service
public class DefaultGeoPhotoRepository implements GeoPhotoRepositoryCustom {

    @Autowired
    EntityManager em;

    public List<GeoPhotoDao> findGeoPhotosByParams(Parameters params){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<GeoPhotoDao> criteriaQuery = criteriaBuilder
                .createQuery(GeoPhotoDao.class);
        Root<Project> projectRoot = criteriaQuery.from(Project.class);
        Join<Project, GeoPhoto> geoPhotoJoin = projectRoot.join(Project_.geoPhotos);
        List<Predicate> predicates = new ArrayList<>();
        List<Selection<?>> multiSelect = new ArrayList<>();
        multiSelect.add(geoPhotoJoin);
        FilterHelper.filterProjectQuery(params, criteriaBuilder, projectRoot, predicates);

        if(predicates.size()>0) {
            Predicate other = criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            criteriaQuery.where(other);
        }

        TypedQuery<GeoPhotoDao> query = em.createQuery(criteriaQuery.multiselect(multiSelect));
        return query.getResultList();
    }
}
