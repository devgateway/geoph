package org.devgateway.geoph.util;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author dbianco
 *         created on mar 10 2016.
 */
public class FilterParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterParser.class);

    public static Map<String, String[]> paramsParse(String params){
        Map<String, String[]> paramsMap = new HashMap<>();
        String[] filters = params.split("&");
        for(String filter:filters){
            String[] parts = filter.split("=");
            paramsMap.put(parts[0], parts[1].split(","));
        }
        return paramsMap;
    }

    public static List<Long> stringArrayToLongList(String[] array){
        List<Long> ret = Lists.transform(Arrays.asList(array), new Function<String, Long>() {
            @Override
            public Long apply(String o) {
                return Long.parseLong(o);
            }
        });
        return ret;
    }

}
