package katas.sort;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class SortTest2 {
    private List<Integer> intList(Integer... ints) {
        return Arrays.asList(ints);
    }

    private void assertSorted(List<Integer> unsorted, List<Integer> sorted) {
        assertThat(sort(unsorted), CoreMatchers.equalTo(sorted));
    }

    private void sortBigList(int n) {
        List<Integer> unsorted = new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            unsorted.add((int) (Math.random() * 10000.0));
        List<Integer> sorted = sort(unsorted);
        sort(sorted);
        for (int i=0; i<n-1; i++)
            assertTrue(sorted.get(i) <= sorted.get(i+1));
            
    }

    @Test
    public void sortTest() {
        assertSorted(intList(), intList());
        assertSorted(intList(1), intList(1));
        assertSorted(intList(2,1), intList(1,2));
        assertSorted(intList(2,1,3), intList(1,2,3));
        assertSorted(intList(3,4,2), intList(2,3,4));
        assertSorted(intList(4,6,5), intList(4,5,6));
        assertSorted(intList(5,4,3), intList(3,4,5));
        assertSorted(intList(6,5,5,7), intList(5,5,6,7));
        sortBigList(500);
    }

    /*
     * Change log:
     * 1. transform: null to constant return value
     */
    private List<Integer> sort(List<Integer> list) {
        List<Integer> sorted = new ArrayList<Integer>();
        if (list.size() == 0) {
            return list;
        } else {
            List<Integer> l = new ArrayList<Integer>();
            Integer m = list.get(0);
            List<Integer> h = new ArrayList<Integer>();
            
            for(int i : list.subList(1, list.size())) {
                if (i > m)
                    h.add(i);
                else
                    l.add(i);
            }
            
            sorted.addAll(sort(l));
            sorted.add(m);
            sorted.addAll(sort(h));
        }
        return sorted;
    }
}
