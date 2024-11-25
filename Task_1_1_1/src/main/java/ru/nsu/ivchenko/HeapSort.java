package ru.nsu.ivchenko;

import java.util.Arrays;
import java.util.Random;

public class HeapSort {
    /**
     * Function that sorts an array.
     *
     * @param arr — array;
     */
    public void heapsort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;

            heapify(arr, i, 0);
        }
    }

    /**
     * Function that swaps elems in binary tree if parent more/less than child.
     *
     * @param arr — heap's array;
     * @param i   — current index in array.
     * @param N   — len of array.
     */
    void heapify(int[] arr, int N, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (right < N && arr[right] > arr[largest]) {
            largest = right;
        }

        if (left < N && arr[left] > arr[largest]) {
            largest = left;
        }

        if (largest != i) {
            int tmp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = tmp;

            heapify(arr, N, largest);
        }
    }

    public static void main(String[] args) {
          /*HeapSort hs = new HeapSort();
          Random random = new Random();

          int size = 10000;
          long[] sizes = new long[10];
          long[] times = new long[10];

          for (int i = 0; i < 10; i++) {
              sizes[i] = size;
              int[] arr = new int[size];

              for (int j = 0; j < size; j++) {
                  arr[j] = random.nextInt();
              }

              long startTime = System.nanoTime();
              hs.heapsort(arr);
              long endTime = System.nanoTime();

              times[i] = endTime - startTime;
              System.out.println("Размер массива: " + size + ", время: " + times[i] + " наносек");
              size = size +20000;
          }

          // Вывод массивов с результатами
          System.out.println("Размеры массивов: " + Arrays.toString(sizes));
          System.out.println("Время выполнения (нс): " + Arrays.toString(times));*/
    }
}