package org.devgateway.geoph.persistence.repository;

import org.devgateway.geoph.model.Sector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

import static org.devgateway.geoph.util.Constants.*;

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

    private List<Sector> sectorInitializer(List<Sector> sectors){
        for(Sector sector:sectors){
            if(sector.getItems()!=null){
                sectorInitializer(sector.getItems());
            }
        }
        return sectors;
    }
}
