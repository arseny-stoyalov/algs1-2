package com.example.entities.search.array;

import com.example.services.tasks.ArrayMethods;
import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class DataStructure implements Collection<Integer> {

    private Integer[] values;

    private int capacity;

    @SneakyThrows
    public DataStructure() {
        values = create(0);
    }

    public DataStructure(Integer[] values) {
        this.values = values;
        this.capacity = values.length;
    }

    private Integer[] create(int size, Integer... array) {
        return Arrays.copyOf(array, size);
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @SneakyThrows
    @Override
    public boolean contains(Object o) {
        boolean ans = false;
        for (Integer e : values) {
            if (e.equals(o)) {
                ans = true;
                break;
            }
        }
        return ans;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {

            int cursor = -1;

            @Override
            public boolean hasNext() {
                return cursor < capacity - 1;
            }

            @Override
            public void remove() {
                if (cursor < 0) return;
                System.arraycopy(values, cursor + 1, values, cursor, size() - 1 - cursor);
                capacity--;
                values = create(capacity, values);
            }

            @Override
            public Integer next() {
                return values[++cursor];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <A> A[] toArray(A[] a) {

        if (a.length < size())
            return (A[]) Arrays.copyOf(values, size(), a.getClass());
        //Weird Collection interface makes me use param of different type
        System.arraycopy(values, 0, a, 0, size());
        if (a.length > size())
            for (int i = size(); i < a.length; i++)
                a[i] = null;
        return a;
    }

    @Override
    public boolean add(Integer value) {
        this.values = create(++capacity, this.values);
        this.values[capacity - 1] = value;
        return true;
    }

    @SneakyThrows
    @Override
    public boolean remove(Object o) {

        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(o)) {
                index = i;
                break;
            }
        }
        if (index < 0) return false;
        System.arraycopy(values, index + 1, values, index, size() - 1 - index);
        capacity--;
        values = create(capacity, values);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        ans.append("DataStructure[ ");
        for (int i = 0; i < size() - 1; i++)
            ans.append(values[i]).append(", ");
        ans.append(values[size() - 1]).append(" ]");
        return ans.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataStructure)) return false;
        return Arrays.equals(values, ((DataStructure) obj).values);
    }

    @SneakyThrows
    @Override
    public boolean containsAll(Collection<?> c) {

        boolean res = true;
        Integer[] sorted = getSorted(values);
        for (Object element : c)
            if (ArrayMethods.interpolationSearch(sorted, (Integer) element) < 0)
                res = false;

        return res;
    }

    @SneakyThrows
    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean changed = false;
        for (Integer element : c) {
            changed = add(element) || changed;
        }
        return changed;
    }

    @SneakyThrows
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object e : c) {
            while (contains(e))
                changed = remove(e) || changed;
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        DataStructure temp = new DataStructure();
        for (Integer e : values) {
            if (c.contains(e)) temp.add(e);
        }
        boolean changed = this.equals(temp);
        this.values = temp.values;
        this.capacity = temp.capacity;
        return changed;
    }

    @Override
    public void clear() {
        this.values = create(0, this.values);
        this.capacity = 0;
    }

    public int search(Integer toFind) {
        return ArrayMethods.interpolationSearch(values, toFind);
    }

    public Integer get(int key) {
        if (key < 0 || key > values.length - 1) return null;
        return values[key];
    }

    private Integer[] getSorted(Integer[] array) {

        Integer[] temp = create(size());
        System.arraycopy(array, 0, temp, 0, size());
        Arrays.sort(temp);
        return temp;
    }

}
