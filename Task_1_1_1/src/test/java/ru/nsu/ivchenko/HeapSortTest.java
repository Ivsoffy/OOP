package ru.nsu.ivchenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HeapSortTest {
    @Test
    public void testSimpleArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] sorted = {1, 2, 3, 4, 5};

        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] sorted = {};
        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {1};
        int[] sorted = {1};
        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {4, 7, 2, 2, 8, 2, 1, 9, 3};
        int[] sorted = {1, 2, 2, 2, 3, 4, 7, 8, 9};
        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted);
    }
}