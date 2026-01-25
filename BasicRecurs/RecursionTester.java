import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.*;
public class RecursionTester {

    /**
     * @author Danny Le
     * @since Monday March 25th, 2024
     * @see singleDigitMultiply
     */
    @Test
    public void testsingleDigitMultiply() {
        int result= Recursion.singleDigitMultiply(6, 4);
        int result2= Recursion.singleDigitMultiply(1, 14);
        int result3= Recursion.singleDigitMultiply(1, 0);
        assertEquals(result, 24);
        assertEquals(result2, 14);
        assertEquals(result3, 0);
    }

    /**
     * @author Danny Le
     * @since Monday March 25th, 2024
     * @see findMinimum
     */
    @Test
    public void testfindMinimum() {
        Students Joe= new Students("Joe", "Mama", 123, 3.5);
        Students John= new Students("John", "John", 321, 2.9);
        Students Josh= new Students("Josh", "Luu", 999, 3.2);
        Students[] students= {Joe, John, Josh};
        Students result= Recursion.findMinimum(students, 3);
        assertEquals(result, Joe);
    }

    /**
     * @author Danny Le
     * @since Monday March 25th, 2024
     * @see longmultiplication
     */
    @Test
    public void testlongmultiplication() {
        int result= Recursion.longmultiplication(124, 16);
        assertEquals(result, 1984);
    }

    /**
     * @author Danny Le
     * @since Wednesday March 27th, 2024
     * @see advance
     */
    @Test
    public void testadvance() {
        TrainCar thegoose= new Caboose(null, 0);
        TrainCar box= new BoxCar(thegoose, 0);
        TrainCar choo= new Engine(box, 0);
        choo.advance(100);
        double result1= thegoose.itsDistanceFromHome;
        double result2= box.itsDistanceFromHome;
        double result3= choo.itsDistanceFromHome;
        assertEquals(result1, 100);
        assertEquals(result2, 100);
        assertEquals(result3, 100);
    }
}
