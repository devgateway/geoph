package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class Export {





    public void export(List<Location> locationList){

        List<ColumnDefinition> columnsDef = new ArrayList<>();
        columnsDef.add(new ColumnDefinition<Long>("ID","getId",   new LongFormatter()));
        columnsDef.add(new ColumnDefinition<Long>("Code","getCode", new LongFormatter()));
        columnsDef.add(new ColumnDefinition<Long>("ID","getName",   new LongFormatter()));

        SimpleValueExtractor<Long,Location> longExtractor=new SimpleValueExtractor<>();

        List list = locationList.stream().map(location -> columnsDef.stream().map(columnDefinition -> columnDefinition.getCell(longExtractor.extract(location, columnDefinition.getGetter()))).collect(Collectors.toList())).collect(Collectors.toList());

        System.out.print(list);
    }
}

