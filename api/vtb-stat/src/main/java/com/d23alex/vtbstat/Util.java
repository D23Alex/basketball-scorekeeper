package com.d23alex.vtbstat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    @SafeVarargs
    public static <T> List<T> concatMultiple(List<? extends T> ... lists) {
        return (List<T>) Arrays.stream(lists).map(List::stream).reduce(Stream::concat).orElseGet(Stream::empty).toList();
    }

    public static Long sum(List<Long> list) {
        return list.stream().reduce(0L, Long::sum);
    }

    public static Date laterDate(Date d1, Date d2) {
        return d1.after(d2) ? d1 : d2;
    }

    public static Date earlierDate(Date d1, Date d2) {
        return d1.after(d2) ? d2 : d1;
    }
}
