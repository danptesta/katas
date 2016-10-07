package katas.sort;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class SortTest1 {
    private List<Integer> intList(Integer... ints) {
        return Arrays.asList(ints);
    }

    private void assertSorted(List<Integer> unsorted, List<Integer> sorted) {
        assertThat(sort(unsorted), is(sorted));
    }

    @Test
    public void sortTest() {
        assertSorted(intList(), intList());
        assertSorted(intList(1), intList(1));
        assertSorted(intList(2,1), intList(1,2));
        assertSorted(intList(1,3,2), intList(1,2,3));
        assertSorted(intList(4,3,2), intList(2,3,4));
        
        sortBigList(25000);
    }

    private void sortBigList(int n) {
        List<Integer> unsorted = new ArrayList<Integer>();
        for(int i=0; i<n; i++)
            unsorted.add((int) (Math.random() * 10000.0));
        List<Integer> sorted = sort(unsorted);
        for (int i=0; i<n-1; i++)
            assertTrue(sorted.get(i) <= sorted.get(i+1));
            
    }

    /*
     * Change log:
     * 1. transform: null to constant return value
     * 2. transform: constant to variable return value
     * 3. transform: split flow to swap first two elements
     * 4. transform: split flow to swap only if > 1 elements
     * 5. transform: if to while to swap all elements in 3 element list
     * 6. transform: if to while to iterate index in 3 element list
     * 7. refactor: extract swap method
     * 8. refactor: remove horrible braces on if block
     * 9. refactor: extract outOfOrder method
     * 10. refactor: while to for loop
     * 11. refactor: remove horrible braces on for block
     */
    private List<Integer> sort(List<Integer> list) {
        for(int size = list.size(); size > 0; size--)
            for (int index = 0; size > index+1; index++)
                if (outOfOrder(list, index)) 
                    swap(list, index);
        return list;
    }

    private boolean outOfOrder(List<Integer> list, int index) {
        return list.get(index) > list.get(index+1);
    }

    private void swap(List<Integer> list, int index) {
        Integer temp = list.get(index);
        list.set(index, list.get(index+1)); // 4. assignment
        list.set(index+1, temp);
    }
}
