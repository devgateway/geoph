package org.devgateway.geoph.persistence;

import org.devgateway.geoph.model.Indicator;
import org.devgateway.geoph.model.IndicatorDetail;
import org.devgateway.geoph.persistence.repository.IndicatorDetailRepository;
import org.devgateway.geoph.persistence.repository.IndicatorRepository;
import org.devgateway.geoph.services.LayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
@Service
public class LayerServiceImpl implements LayerService{

    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;


    @Override
    public List<Indicator> getLayerList() {
        return indicatorRepository.findAll();
    }

    @Override
    public List<IndicatorDetail> getLayerData(long indicatorId) {
        return indicatorDetailRepository.findByIndicatorId(indicatorId);
    }
}
