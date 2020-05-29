package io.github.nosequel.schematic.util;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class JavaUtils {

    /**
     * Split a list into multiple chunks
     *
     * @param list      the list
     * @param chunkSize the size of the chunks
     * @param <T>       the type of the list
     * @return the new list of chunks
     */
    public static <T> Collection<List<T>> splitList(List<T> list, int chunkSize) {
        final AtomicInteger current = new AtomicInteger();

        return list.stream()
                .collect(Collectors.groupingBy(number -> current.getAndIncrement() / chunkSize))
                .values();
    }
}