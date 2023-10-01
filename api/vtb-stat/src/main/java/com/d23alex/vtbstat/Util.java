package com.d23alex.vtbstat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Util {
    @SafeVarargs
    public static <T> List<T> concatMultiple(List<? extends T> ... lists) {
        return (List<T>) Arrays.stream(lists).map(List::stream).reduce(Stream::concat).orElseGet(Stream::empty).toList();
    }

    public static Long sum(List<Long> list) {
        return list.stream().reduce(0L, Long::sum);
    }
}
