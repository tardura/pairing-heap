import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class PairingHeapTest {

    private PairingHeap<Integer, String> heapToTest;

    @Before
    public void setup(){
        heapToTest = new PairingHeap<Integer, String>();
    }

    @Test
    public void testInsertingInAnEmptyHeap(){
        heapToTest.insert(1, "One");
        assertThat(heapToTest.getMin().get(), is(notNullValue()));
        assertThat(heapToTest.getMin().get(), is("One"));
    }

    @Test
    public void testInsertingAHigherValueInHeapWithOne(){
        heapToTest.insert(1, "One");
        assertThat(heapToTest.getMin().get(), is("One"));
        heapToTest.insert(2, "Two");
        assertThat(heapToTest.getMin().get(), is("One"));
    }

    @Test
    public void testInsertingALessValueInHeapWithOne(){
        heapToTest.insert(1, "One");
        assertThat(heapToTest.getMin().get(), is("One"));
        heapToTest.insert(0, "Zero");
        assertThat(heapToTest.getMin().get(), is("Zero"));
    }

    @Test
    public void testInsertingAnEqualValueInHeapWithOne(){
        heapToTest.insert(1, "One");
        assertThat(heapToTest.getMin().get(), is("One"));
        heapToTest.insert(1, "Second One");
        assertThat(heapToTest.getMin().get(), is("One"));
    }

    @Test
    public void testDeletingMinFromEmptyHeapIsNull(){
        assertThat(heapToTest.deleteMin().isPresent(), is(false));
    }

    @Test
    public void testDeletingMinFromHeapWithOneValueIsCorrectValueAndHeapEndsUpEmpty(){
        heapToTest.insert(1, "One");
        assertThat(heapToTest.getMin().get(), is("One"));
        assertThat(heapToTest.deleteMin().get(), is("One"));
        assertThat(heapToTest.getMin().isPresent(), is(false));
        assertThat(heapToTest.deleteMin().isPresent(), is(false));
    }

    @Test
    public void testActualDeleteMinWithEvenNumberChildren(){
        heapToTest.insert(1, "One");
        heapToTest.insert(2, "Two");
        heapToTest.insert(3, "Three");
        heapToTest.insert(4, "Four");
        heapToTest.insert(5, "Five");
        assertThat(heapToTest.getMin().get(), is("One"));
        assertThat(heapToTest.deleteMin().get(), is("One"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Two"));
        assertThat(heapToTest.deleteMin().get(), is("Two"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Three"));
        assertThat(heapToTest.deleteMin().get(), is("Three"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Four"));
        assertThat(heapToTest.deleteMin().get(), is("Four"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Five"));
        assertThat(heapToTest.deleteMin().get(), is("Five"));
        assertThat(heapToTest.getMin().isPresent(), is(false));
    }

    @Test
    public void testActualDeleteMinWithOddNumberChildren(){
        heapToTest.insert(1, "One");
        heapToTest.insert(2, "Two");
        heapToTest.insert(3, "Three");
        heapToTest.insert(4, "Four");
        heapToTest.insert(5, "Five");
        heapToTest.insert(6, "Six");
        assertThat(heapToTest.getMin().get(), is("One"));
        assertThat(heapToTest.deleteMin().get(), is("One"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Two"));
        assertThat(heapToTest.deleteMin().get(), is("Two"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Three"));
        assertThat(heapToTest.deleteMin().get(), is("Three"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Four"));
        assertThat(heapToTest.deleteMin().get(), is("Four"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Five"));
        assertThat(heapToTest.deleteMin().get(), is("Five"));
        assertThat(heapToTest.getMin().isPresent(), is(true));
        assertThat(heapToTest.getMin().get(), is("Six"));
        assertThat(heapToTest.deleteMin().get(), is("Six"));
        assertThat(heapToTest.getMin().isPresent(), is(false));

    }

}

