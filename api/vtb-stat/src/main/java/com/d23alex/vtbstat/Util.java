package com.d23alex.vtbstat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Util {
    @SafeVarargs
    public static <T> List<T> concatMultiple(List<? extends T> ... streams) {
        return (List<T>) Arrays.stream(streams).map(List::stream).reduce(Stream::concat).orElseGet(Stream::empty).toList();
    }
}
