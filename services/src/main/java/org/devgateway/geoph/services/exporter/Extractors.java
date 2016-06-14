package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.Extractor;
import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.Currency;

import java.util.Map;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public class Extractors {


    public static Extractor<String> stringExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                return properties.get(getter).toString();
            }
        };
    }

    public static Extractor<String> agencyExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                Agency agency = (Agency) properties.get(getter);
                return agency.getName();
            }
        };
    }


    public static Extractor<String> currencyExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                if (value instanceof Currency) {
                    Currency currency = (Currency) value;
                    return currency.getName();
                } else {
                    return "";
                }
            }
        };
    }


    public static Extractor<Long> longExtractor(final String getter) {
        return new Extractor<Long>() {
            @Override
            public Long extract(Map<String, Object> properties) {
                return (Long) properties.get(getter);
            }
        };
    }

    public static Extractor<Double> doubleExtractor(final String getter) {
        return new Extractor<Double>() {
            @Override
            public Double extract(Map<String, Object> properties) {
                return (Double) properties.get(getter);
            }
        };
    }
}
