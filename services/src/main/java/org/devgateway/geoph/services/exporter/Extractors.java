package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.Extractor;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.*;
import org.hibernate.collection.internal.PersistentSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.devgateway.geoph.core.constants.Constants.CSV_RECORD_SEPARATOR;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public class Extractors {


    private static final char REPLACE_CHAR = '/';

    public static Extractor<String> stringExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                return properties.get(getter)!=null?properties.get(getter).toString().replace(CSV_RECORD_SEPARATOR, REPLACE_CHAR):"";
            }
        };
    }


    public static Extractor<List<String>> implementingAgencyExtractor(final String getter) {
        return new Extractor<List<String>>() {
            @Override
            public List<String> extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                List<String> names = new ArrayList<>();
                if (value instanceof PersistentSet) {
                    names = ((Set<ImplementingAgency>) value).stream().map(implementingAgency -> implementingAgency.getName()).collect(Collectors.toList());
                }
                return names;
            }
        };
    }

    public static Extractor<String> classificationExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                if (value instanceof Classification) {
                    Classification classification = (Classification) value;
                    return classification.getName();
                } else {
                    return "";
                }
            }
        };
    }

    public static Extractor<List<String>> sectorExtractor(final String getter) {
        return new Extractor<List<String>>() {
            @Override
            public List<String> extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                List<String> names = new ArrayList<>();
                if (value instanceof PersistentSet) {
                    names = ((Set<Sector>) value).stream().map(sector -> sector.getName()).collect(Collectors.toList());
                }
                return names;
            }
        };
    }

    public static Extractor<Double> commitmentExtractor(final String getter) {
        return trxExtractor(getter, TransactionTypeEnum.COMMITMENT);
    }

    public static Extractor<Double> disbursementExtractor(final String getter) {
        return trxExtractor(getter, TransactionTypeEnum.DISBURSEMENT);
    }

    public static Extractor<Double> expenditureExtractor(final String getter) {
        return trxExtractor(getter, TransactionTypeEnum.EXPENDITURE);
    }

    public static Extractor<Double> trxExtractor(final String getter, TransactionTypeEnum trxTypeEnum) {
        return new Extractor<Double>() {
            @Override
            public Double extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                List<Double> trxValues = new ArrayList<>();
                if (value instanceof PersistentSet) {
                    trxValues = ((Set<Transaction>) value)
                            .stream()
                            .filter(transaction -> {
                                if(transaction.getTransactionType().equals(trxTypeEnum)){
                                    return true;
                                }
                                return false;
                            })
                            .map(transaction -> transaction.getAmount())
                            .collect(Collectors.toList());
                }
                Double ret = trxValues.stream().reduce((p1, p2) -> p1 + p2).orElse(0D);
                return ret;
            }
        };
    }


    public static Extractor<String> agencyExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                if (value instanceof Agency) {
                    Agency agency = (Agency) value;
                    return agency.getName();
                } else {
                    return "";
                }
            }
        };
    }

    public static Extractor<String> statusExtractor(final String getter) {
        return new Extractor<String>() {
            @Override
            public String extract(Map<String, Object> properties) {
                Object value = properties.get(getter);
                if (value instanceof Status) {
                    Status status = (Status) value;
                    return status.getName();
                } else {
                    return "";
                }
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
                Long ret = null;
                if(properties.get(getter) instanceof Long){
                    ret = (Long) properties.get(getter);
                }
                return ret;
            }
        };
    }

    public static Extractor<Double> doubleExtractor(final String getter) {
        return new Extractor<Double>() {
            @Override
            public Double extract(Map<String, Object> properties) {
                Double ret = null;
                if(properties.get(getter) instanceof Double){
                    ret = (Double) properties.get(getter);
                }
                return ret;
            }
        };
    }

    public static Extractor<Float> floatExtractor(String getter) {
        return new Extractor<Float>() {
            @Override
            public Float extract(Map<String, Object> properties) {
                Float ret = null;
                if(properties.get(getter) instanceof Float){
                    ret = (Float) properties.get(getter);
                }
                return ret;
            }
        };
    }
}
