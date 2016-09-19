package org.devgateway.geoph.services;

import org.devgateway.geoph.core.repositories.IndicatorDetailRepository;
import org.devgateway.geoph.core.repositories.IndicatorRepository;
import org.devgateway.geoph.core.repositories.LocationRepository;
import org.devgateway.geoph.core.response.IndicatorResponse;
import org.devgateway.geoph.core.services.LayerService;
import org.devgateway.geoph.model.Indicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dbianco
 *         created on abr 25 2016.
 */
@Service
public class LayerServiceImpl implements LayerService {

    private static final int LONG = 0;
    private static final int LAT = 1;


    @Autowired
    IndicatorRepository indicatorRepository;

    @Autowired
    IndicatorDetailRepository indicatorDetailRepository;

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Indicator> getIndicatorsList() {
        return indicatorRepository.findAll();
    }

    @Override
    public IndicatorResponse getIndicatorById(Long id) {
        IndicatorResponse response = new IndicatorResponse(indicatorRepository.findOne(id));
        if(response.getId()!=null){
            response.addDetails(indicatorDetailRepository.findByIndicatorId(id));
        }
        return response;
    }

    @Override
    public void deleteIndicator(Long id) {
        indicatorDetailRepository.delete(indicatorDetailRepository.findByIndicatorId(id));
        indicatorRepository.delete(id);
    }


}
