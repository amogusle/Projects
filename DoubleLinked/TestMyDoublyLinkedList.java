import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import java.io.*;

public class TestMyDoublyLinkedList {
    
    private MyDoublyLinkedList<String> doubly;

    @Before
    private void setup() {
        doubly= new MyDoublyLinkedList<>();

        doubly.add("whoo");
        doubly.add("I'm");
        doubly.add("about");
        doubly.add("to");
        doubly.add("make");
        doubly.add("a");
        doubly.add("name");
        doubly.add("for");
        doubly.add("yeah");
        doubly.add("myself", 8);
    }

    @Test
    public void checkdata() {
        int cursize= doubly.size();
        assertEquals(9, cursize);

        String current= doubly.remove(9);
        assertEquals("yeah", current);

        current= doubly.get(0);
        assertEquals("whoo", current);

        boolean tf= doubly.contains(current);
        assertEquals(true, tf);

        current= doubly.get(1);
        assertEquals("I'm", current);

        current= doubly.get(2);
        assertEquals("about", current);

        current= doubly.get(3);
        assertEquals("to", current);

        cursize= doubly.indexOf(current);
        assertEquals(3, cursize);

    }

}
