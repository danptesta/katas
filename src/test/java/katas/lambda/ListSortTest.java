package katas.lambda;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ListSortTest {
    private Developer d1;
    private Developer d2;
    private Developer d3;
    private List<Developer> actual;

    @Before
    public void setUp() {
        d1 = new Developer("Joe", new BigDecimal("70000"), 33);
        d2 = new Developer("Jane", new BigDecimal("80000"), 29);
        d3 = new Developer("Jon", new BigDecimal("75000"), 35);
        actual = new ArrayList<Developer>();
        actual.add(d1);
        actual.add(d2);
        actual.add(d3);
    }

    private void assertSorted(Comparator<? super Developer> comparator, Developer... sorted) {
        actual.sort(comparator);
        assertThat(actual, equalTo(Arrays.asList(sorted)));
    }

    @Test
    public void sortByAge() {
        assertSorted((o1, o2)->o1.getAge()-o2.getAge(), d2, d1, d3);
    }

    @Test
    public void sortByName() {
        assertSorted((o1, o2)->o1.getName().compareTo(o2.getName()), d2, d1, d3);
    }
    
    @Test public void sortBySalary() {
        assertSorted((o1, o2)->o1.getSalary().compareTo(o2.getSalary()), d1, d3, d2);
    }
}
