package com.lnt.ems.evse.util;

import java.util.Arrays;

import static java.lang.String.format;

public class BooleanUtils {

    public BooleanUtils() {
        throw new UnsupportedOperationException("Utility class can not be instantiated.");
    }

    public enum YesNo {
        YES("Y"),
        NO("N");

        private final String value;

        YesNo(String value) {
            this.value = value;
        }

        public static boolean isYes(String value) {
            return YES.equals(of(value));
        }

        public static boolean isNo(String value) {
            return !isYes(value);
        }

        public static YesNo of(String value) {
            return Arrays.stream(values()).filter(v -> v.value.equals(value)).findFirst()
                    .orElseThrow(() -> new UnsupportedOperationException(format("Value [%s] is unsupported.", value)));
        }

        public String getValue() {
            return value;
        }
    }
}
