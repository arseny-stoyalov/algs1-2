package com.example.services;

import com.example.entities.sort.SortName;
import com.example.services.tasks.Sort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SortTest {

    private int[][] testArray;
    private final int[][] expected = {{1, 2, 3, 4, 8}, {1, 2, 2, 4, 7}, {2}, {1, 2, 2, 3, 5, 6}};

    @BeforeEach
    public void setUp(){
        testArray = new int[][]{{2, 3, 1, 8, 4}, {2, 2, 1, 4, 7}, {2}, {5, 2, 6, 3, 1, 2}};
    }

    @Test
    public void testInsertionSort(){
        Sort.insertionSort(testArray[0]);
        assertArrayEquals(expected[0], testArray[0]);
    }

    @Test
    public void testQuickSort() {
        Sort.quickSort(testArray[0]);
        assertArrayEquals(expected[0], testArray[0]);
    }

    @Test
    public void testMatrixInsertionSort(){
        Sort.sortMatrix(testArray, SortName.INSERTION);
        assertArrayEquals(expected, testArray);
    }

    @Test
    public void testMatrixQuickSort(){
        Sort.sortMatrix(testArray, SortName.QUICK);
        assertArrayEquals(expected, testArray);
    }

}