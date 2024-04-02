import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynArrayTest {
    private DynArray<Integer> dynArray;

    @Before
    public void setUp() {
        dynArray = new DynArray<>(Integer.class);
        dynArray.append(1);
        dynArray.append(2);
        dynArray.append(3);
    }


    @Test
    public void testInsertWithinCapacity() {
        dynArray.insert(10, 1);

        assertEquals(10, (int) dynArray.getItem(1));
        assertEquals(4, dynArray.count);
        assertEquals(16, dynArray.capacity);
    }

    @Test
    public void testInsertExceedingCapacity() {
        DynArray<Integer> dynArray = new DynArray<>(Integer.class);
        for (int i = 0; i < 16; i++) {
            dynArray.append(i);
        }

        dynArray.insert(100, 15);

        assertEquals(100, (int) dynArray.getItem(15));
        assertEquals(17, dynArray.count);
        assertEquals(32, dynArray.capacity);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBounds() {
        dynArray.insert(10, 5);
    }

    @Test
    public void testRemoveNoBufferResize() {
        dynArray.remove(1);

        assertEquals(2, dynArray.count);
        assertEquals(16, dynArray.capacity);
    }

    @Test
    public void testRemoveWithBufferResize() {
        DynArray<Integer> dynArray = new DynArray<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            dynArray.append(i);
        }

        dynArray.remove(16);
        dynArray.remove(15);

        assertEquals(15, dynArray.count);
        assertEquals(21, dynArray.capacity);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBounds() {
        dynArray.remove(3);
    }
}

