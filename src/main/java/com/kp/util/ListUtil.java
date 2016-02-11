package com.kp.util;

import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by turgaycan on 9/28/15.
 */
public class ListUtil {

    private ListUtil() {
    }

    public static <T extends Serializable> List<T> defensiveSubList(List<T> list, int size) {

        if (CollectionUtils.isEmpty(list) || list.size() < size) {
            return list;
        }

        return list.subList(0, size);
    }

    public static <T extends Serializable> List<T> defensiveSubList(List<T> list, int startIndex, int endIndex) {

        if (CollectionUtils.isEmpty(list) || list.size() < endIndex) {
            return list;
        }

        return list.subList(startIndex, endIndex);
    }
}
