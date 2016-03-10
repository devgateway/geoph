package org.devgateway.geoph.util;

import com.google.common.base.Function;
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
