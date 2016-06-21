package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.Formatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.devgateway.geoph.core.constants.Constants.COMMA;

/**
 * Created by Sebastian Dimunzio on 6/9/2016.
 */
@Service
public class Formatters {

    static String dateFormat;

    static String numberFormat;

    static String decimalFormat;

    static String amountFormat;

    @Value("${export.format.date}")
    public void setDateFormat(String dateFormat) {
        Formatters.dateFormat = dateFormat;
    }

    @Value("${export.format.number}")
    public void setNumberFormat(String numberFormat) {
        Formatters.numberFormat = numberFormat;
    }

    @Value("${export.format.decimal}")
    public void setDecimalFormat(String decimalFormat) {
        Formatters.decimalFormat = decimalFormat;
    }

    @Value("${export.format.amount}")
    public void setAmountFormat(String amountFormat) {
        Formatters.amountFormat = amountFormat;
    }

    public static Formatter stringFormatter() {
        return new Formatter<String>() {
            @Override
            public String format(String value) {
                return value;
            }
        };
    }

    public static Formatter stringArrayFormatter() {
        return new Formatter<List<String>>() {
            @Override
            public String format(List<String> value) {
                return value.stream().collect(Collectors.joining(COMMA));
            }
        };
    }

    public static Formatter intFormatter() {
        final NumberFormat formatter = new DecimalFormat(numberFormat);
        return new Formatter<Integer>() {
            @Override
            public String format(Integer value) {
                String ret = null;
                if(value != null){
                    ret = formatter.format(value);
                }
                return ret;
            }
        };
    }

    public static Formatter longFormatter() {
        final NumberFormat formatter = new DecimalFormat(numberFormat);
        return new Formatter<Long>() {
            @Override
            public String format(Long value) {
                String ret = null;
                if(value != null){
                    ret = formatter.format(value);
                }
                return ret;
            }
        };
    }

    public static Formatter doubleFormatter() {
        final DecimalFormat formatter = new DecimalFormat(decimalFormat);
        return new Formatter<Double>() {
            @Override
            public String format(Double value) {
                String ret = null;
                if(value != null) {
                    ret = formatter.format(value);
                }
                return ret;
            }
        };
    }

    public static Formatter floatFormatter() {
        final DecimalFormat formatter = new DecimalFormat(decimalFormat);
        return new Formatter<Float>() {
            @Override
            public String format(Float value) {
                String ret = null;
                if(value != null) {
                    ret = formatter.format(value);
                }
                return ret;
            }
        };
    }

    public static Formatter amountFormatter() {
        final DecimalFormat formatter = new DecimalFormat(amountFormat);
        return new Formatter<Double>() {
            @Override
            public String format(Double value) {
                String ret = null;
                if(value != null) {
                    ret = formatter.format(value);
                }
                return ret;
            }
        };
    }

    public static Formatter dateFormatter() {
        final DateFormat formatter = new SimpleDateFormat(dateFormat);
        return new Formatter<Date>() {
            @Override
            public String format(Date value) {
                String ret = null;
                if(value != null) {
                    ret = formatter.format(value);
                }
                return ret;
            }
        };
    }
}
