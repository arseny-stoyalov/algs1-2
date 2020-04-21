package com.example.services;

import com.example.entities.search.array.DataStructure;
import com.example.entities.sort.Matrix;
import com.example.entities.sort.SortName;
import com.example.services.tasks.ArrayMethods;
import com.example.services.tasks.Sort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AlgorithmTester {

    public String[] testIntSearch(String... strArgs) {

        String[] res = new String[4];

        List<Integer[]> args = parseStringArgs(strArgs);

        DataStructure data = new DataStructure(args.get(0));
        List<Integer> toFind = List.of(args.get(1));

        List<Integer> toAdd = null;
        if (args.get(2)[0] != null) toAdd = List.of(args.get(2));
        List<Integer> toRemove = null;
        if (args.get(3)[0] != null) toRemove = List.of(args.get(3));
        StringBuilder builder = new StringBuilder();

        Integer[] sorted = new Integer[args.get(0).length];
        System.arraycopy(args.get(0), 0, sorted, 0, args.get(0).length);
        Arrays.sort(sorted);
        builder.append("Find ").append(toFind.get(0)).append("\n");
        builder.append("Index: ").append(ArrayMethods.interpolationSearch(sorted, toFind.get(0)))
                .append("\n");
        Method interpolation = null;
        Method binary = null;
        try {
            interpolation = ArrayMethods.class.getMethod("interpolationSearch", Integer[].class, Integer.class);
            binary = Arrays.class.getMethod("binarySearch", int[].class, int.class);
        } catch (NoSuchMethodException e) {
            log.error("Failed to invoke given method");
        }

        if (interpolation == null || binary == null) return null;


        int[] temp = new int[sorted.length];
        for (int i = 0; i < sorted.length; i++) {
            temp[i] = sorted[i];
        }

        builder.append("Interpolation search: ")
                .append(getAlgTime(interpolation, sorted, toFind.get(0))).append(" ns\n");
        builder.append("Default search: ")
                .append(getAlgTime(binary, temp, toFind.get(0))).append(" ns\n");
        res[1] = builder.toString();

        builder.delete(0, builder.length());

        if (toAdd == null) {
            res[2] = "No elements added";
        } else {
            data.addAll(toAdd);
            builder.append("After adding: ")
                    .append(data.toString()).append("\n");
            res[2] = builder.toString();
            builder.delete(0, builder.length());
        }

        if (toRemove == null) {
            res[3] = "No elements removed";
        } else {
            data.removeAll(toRemove);
            builder.append("After removing: ")
                    .append(data.toString()).append("\n");
            res[3] = builder.toString();
            builder.delete(0, builder.length());
        }

        builder.append("Sorted result: ").append(Arrays.toString(sorted));
        res[0] = builder.toString();

        return res;
    }

    public String[] testSort(Matrix aMatrix) {
        String[] result = new String[4];
        Method algorithm = null;
        try {
            algorithm = Sort.class.getMethod("sortMatrix", int[][].class, SortName.class);
        } catch (NoSuchMethodException e) {
            log.error("Failed to invoke given method");
        }

        if (algorithm == null) return null;

        int[][] defaultSort = copyMatrix(aMatrix.getIntValue());
        long defaultTime = getAlgTime(algorithm, defaultSort, SortName.DEFAULT);

        int[][] insertionSort = copyMatrix(aMatrix.getIntValue());
        long insertionTime = getAlgTime(algorithm, insertionSort, SortName.INSERTION);

        int[][] quickSort = copyMatrix(aMatrix.getIntValue());
        long quickTime = getAlgTime(algorithm, quickSort, SortName.QUICK);

        aMatrix.setIntValue(defaultSort);
        result[0] = "Result: \n" + aMatrix.getStrValue();
        result[1] = "Default sort algorithm: " + defaultTime + " ns";
        result[2] = "Insertion sort algorithm: " + insertionTime + " ns";
        result[3] = "Quick sort algorithm: " + quickTime + " ns";
        return result;
    }

    private int[][] copyMatrix(int[][] matrix) {
        int[][] res = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            res[i] = new int[matrix[i].length];
            System.arraycopy(matrix[i], 0, res[i], 0, matrix[i].length);
        }
        return res;
    }

    /**
     * Parses string equivalent of Integer array to Integer
     * array, does not check if it's possible
     *
     * @param args String value of Integer array
     * @return Integer array
     */
    private static List<Integer[]> parseStringArgs(String... args) {
        List<Integer[]> res = new ArrayList<>();
        for (String arg : args) {
            String pretty = arg.trim();
            pretty = pretty.replaceAll("\\p{Blank}{2,}", " ");
            String[] arr = pretty.split("\\p{Blank}");
            Integer[] ints = new Integer[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].isEmpty())
                    ints[i] = null;
                else
                    ints[i] = Integer.parseInt(arr[i]);
            }
            res.add(ints);
        }
        return res;
    }

    private long getAlgTime(Method algorithm, Object... args) {

        if (!Modifier.isStatic(algorithm.getModifiers())) return -1;
        long start = System.nanoTime();
        try {
            algorithm.invoke(null, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            log.error("Failed to invoke {} method", algorithm.getName());
        }
        long end = System.nanoTime();
        return end - start;
    }

}
