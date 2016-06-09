package org.devgateway.geoph.services.exporter;

import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class DateFormatter implements Formatter<Date> {

    @Value("${format.date}")
    String dateFormat;
       public String format(Date value) {
        return new SimpleDateFormat(dateFormat).format(value);
    }
}
