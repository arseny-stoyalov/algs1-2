package com.example.services;

import com.example.entities.Matrix;
import com.example.entities.SortName;
import org.springframework.stereotype.Service;

@Service
public class AlgorithmTester {

    public String[] testSort(Matrix aMatrix){
        String[] result = new String[4];

        int[][] defaultSort = copy(aMatrix.getIntValue());
        long defaultTime = getSortTime(defaultSort, SortName.DEFAULT);

        int[][] insertionSort = copy(aMatrix.getIntValue());
        long insertionTime = getSortTime(insertionSort, SortName.INSERTION);

        int[][] quickSort = copy(aMatrix.getIntValue());
        long quickTime = getSortTime(quickSort, SortName.QUICK);

        aMatrix.setIntValue(defaultSort);
        result[0] = aMatrix.getStrValue();
        result[1] = String.valueOf(defaultTime);
        result[2] = String.valueOf(insertionTime);
        result[3] = String.valueOf(quickTime);
        return result;
    }

    private int[][] copy(int[][] matrix){
        int[][] res = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new int[matrix[i].length];
            System.arraycopy(matrix[i], 0, res[i], 0, matrix[i].length);
        }
        return res;
    }

    private long getSortTime(int[][] matrix, SortName sortName){
        long start = System.nanoTime();
        Sort.sortMatrix(matrix, sortName);
        long end = System.nanoTime();
        return end - start;
    }

}
