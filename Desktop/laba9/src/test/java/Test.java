import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class Test {

    @BeforeClass
    public static void Setup() { }

    @org.junit.Test
    public void Multiply() {
        MyClass tester = new MyClass();
        assertEquals("3 x 4 must be 12", 12, tester.multiply(3, 4));
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void test2() {
        MyClass tester = new MyClass();
        assertEquals("1000 x 7 must be 7000", 7000, tester.multiply(1000, 7));
    }


    @AfterClass
    public static void Cleanup() { }

}