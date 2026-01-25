import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import java.io.*;

public class TestMap {
    private Map<String, Integer> testmap;

    @Before
    private void setup() {
        testmap= new Map<>();
        testmap.put("a", 1);
        testmap.put("b", 2);
        testmap.put("c", 3);
    }

    @Test
    public void checkdata() {

        int current= testmap.get("a");
        assertEquals(1 , current);

        current= testmap.get("b");
        assertEquals(2, current);

        current= testmap.get("c");
        assertEquals(3, current);

        assertFalse(testmap.isEmpty());

        testmap.makeEmpty();

        assertTrue(testmap.isEmpty());
    }
}