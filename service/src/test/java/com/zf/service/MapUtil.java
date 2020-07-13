package com.zf.service;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class MapUtil {
    public static <T> List<T> mapToAnotherList(List<?> list, Class<T> tClass) {
        List<T> result = Lists.newArrayList();

        if (list == null || list.size() == 0) {
            return result;
        }
        try {
            for (Object item : list) {
                T mapItem = tClass.newInstance();
                BeanUtils.copyProperties(item, mapItem);
                result.add(mapItem);
            }
        } catch (Exception ex) {
            throw new RuntimeException("map list error", ex);
        }

        return result;
    }
}
