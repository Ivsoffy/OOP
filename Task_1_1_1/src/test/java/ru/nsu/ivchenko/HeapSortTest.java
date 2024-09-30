package ru.nsu.ivchenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class HeapSortTest {
    @Test
    public void testSimpleArray() {
        int[] arr = {5, 4, 3, 2, 1};
        int[] sorted_arr = {1, 2, 3, 4, 5};

        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted_arr);
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int[] sorted_arr = {};
        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted_arr);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {1};
        int[] sorted_arr = {1};
        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted_arr);
    }

    @Test
    public void testArrayWithDuplicates() {
        int[] arr = {4, 7, 2, 2, 8, 2, 1, 9, 3};
        int[] sorted_arr = {1, 2, 2, 2, 3, 4, 7, 8, 9};
        HeapSort hs = new HeapSort();
        hs.heapsort(arr);
        assertArrayEquals(arr, sorted_arr);
    }
}