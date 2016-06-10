package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.Formatter;
import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
public class Formatters {
    @Value("${export.format.date}")
    static String dateFormat;

    public static Formatter stringFormatter() {
        return new Formatter<String>() {
            @Override
            public String format(String value) {
                return value;
            }
        };
    }

    public static Formatter longFormatter() {
        return new Formatter<Long>() {
            @Override
            public String format(Long value) {
                return value.toString();
            }
        };
    }



    public static Formatter dateFormmater() {
        final SimpleDateFormat simpleFormater=new SimpleDateFormat(dateFormat);
        return new Formatter<Date>() {
            @Override
            public String format(Date value) {
                return simpleFormater.format(value);
            }
        };
    }
}
