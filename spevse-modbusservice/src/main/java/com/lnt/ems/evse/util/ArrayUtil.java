package com.lnt.ems.evse.util;

import java.util.Comparator;
import java.util.List;

/**
 * @author Mitul Jain
 * Utility class for sorting of list of strings.
 */
public class ArrayUtil {

    /**
     * @param list List<String>
     * @author Mitul Jain
     * Method for sorting of list of strings.
     */
    public static void sortArray(List<String> list) {
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }
}