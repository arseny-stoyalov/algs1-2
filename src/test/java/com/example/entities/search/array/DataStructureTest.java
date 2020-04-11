package com.example.entities.search.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DataStructureTest {

    private DataStructure data = new DataStructure();

    private ArrayList<Integer> list = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        //data
        data.add(3);
        data.add(2);
        data.add(1);
        data.add(6);
        data.add(3);
        data.add(7);
        //list
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(5);
    }

    @AfterEach
    public void clearArray(){
        data.clear();
        list.clear();
    }

    @Test
    public void search(){

    }

    @Test
    void size() {
        log.info("Testing size method...");
        assertEquals(6, data.size());
        log.info("Successful");
    }

    @Test
    void isEmpty() {
        assertFalse(data.isEmpty());
        data.clear();
        assertNull(data.get(0));
    }

    @Test
    void contains() {
        assertTrue(data.contains(list.get(0)));
    }

    @Test
    void iterator() {
        //TODO: Test iterator implementation
    }

    @Test
    void toArray() {
        Integer[] temp = {3, 2, 1, 6, 3, 7};
        assertArrayEquals(temp, data.toArray());
    }

    @Test
    void toArrayWithArgs() {

    }

    @Test
    void add() {

        log.info("Testing adding...\nInitial: {}", data);
        data.add(5);
        assertEquals(3, data.get(0));
        assertNotEquals(3, data.get(1));
        assertEquals(7, data.size());
        log.info("After adding 5: {}", data);
    }

    @Test
    void remove() {

        log.info("Testing removing...\nInitial: {}", data);
        data.remove(1);
        assertEquals(5, data.size());
        assertEquals(6, data.get(2));
        log.info("DataStructure after removal of 1: {}", data);
    }

    @Test
    void containsAll() {
        assertFalse(data.containsAll(list));
        list.remove(Integer.valueOf(5));
        assertTrue(data.containsAll(list));
    }

    @Test
    void addAll() {

        log.info("Testing addingAll...\nInitial: {}", data);
        data.addAll(list);
        assertEquals(10, data.size());
        assertNotNull(data.get(7));
        log.info("DataStructure after adding {} : {}", list, data);

    }

    @Test
    void removeAll() {

        log.info("Testing removingAll...\nInitial: {}", data);
        ArrayList<Integer> toRemove = new ArrayList<>();
        toRemove.add(3);
        toRemove.add(1);
        toRemove.add(2);
        data.removeAll(toRemove);
        assertFalse(data.contains(toRemove.get(0)));
        log.info("After removing {}: {}", toRemove, data.toString());
    }

    @Test
    void retainAll() {
        //TODO: Test retainingAll
    }

    @Test
    void clear() {
        data.clear();
        assertEquals(0, data.size());
        assertNull( data.get(0));
    }

    @Test
    void get() {
        assertEquals(3, data.get(0));
    }
}