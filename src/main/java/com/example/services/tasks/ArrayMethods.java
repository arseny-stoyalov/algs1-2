package com.example.services.tasks;

public final class ArrayMethods {

    public static Integer interpolationSearch(Integer[] sortedArray, Integer toFind){

        int mid;
        int low = 0;
        int high = sortedArray.length - 1;

        while (sortedArray[low] < toFind && sortedArray[high] > toFind) {
            mid = low + ((toFind - sortedArray[low]) * (high - low)) / (sortedArray[high] - sortedArray[low]);

            if (sortedArray[mid] < toFind)
                low = mid + 1;
            else if (sortedArray[mid] > toFind)
                high = mid - 1;
            else
                return mid;
        }

        if (sortedArray[low].equals(toFind))
            return low;
        if (sortedArray[high].equals(toFind))
            return high;

        return -1; // Not found
    }
}
