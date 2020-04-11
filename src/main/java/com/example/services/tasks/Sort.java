package com.example.services.tasks;

import com.example.entities.sort.SortIntMatrix;
import com.example.entities.sort.SortName;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public final class Sort {

    //-------------------------INSERTION-----------------------------
    public static void insertionSort(int[] array) {

        int i, j;
        for (i = 0; i < array.length; i++) {
            int temp = array[i];
            j = i;
            while (j > 0 && array[j - 1] > temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    //-------------------------QUICK-----------------------------
    public static void quickSort(int[] array) {
        recQuickSort(0, array.length - 1, array);
    }

    private static void recQuickSort(int left, int right, int[] array) {

        if (right - left > 0) {
            int partition = separate(left, right, array[right], array);
            recQuickSort(left, partition - 1, array);
            recQuickSort(partition + 1, right, array);
        }
    }

    private static int separate(int left, int right, int pivot, int[] array) {

        pivot = array[right];
        int i = (left - 1);
        for (int j = left; j <= right - 1; j++) {
            if (array[j] < pivot) {
                i++;
                swap(i, j, array);
            }
        }
        swap(i + 1, right, array);
        return (i + 1);
    }

    private static void swap(int first, int second, int[] array) {
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    //-------------------------MATRIX-SORT-----------------------------
    public static void sortMatrix(int[][] matrix, SortName sortName) {
        SortIntMatrix sortAlg = (int[][] m) -> {
            for (int[] lines : m)
                switch (sortName) {
                    case INSERTION:
                        Sort.quickSort(lines);
                        break;
                    case QUICK:
                        Sort.insertionSort(lines);
                        break;
                    default:
                        Arrays.sort(lines);
                        break;
                }
        };
        sortAlg.sort(matrix);
    }

}
