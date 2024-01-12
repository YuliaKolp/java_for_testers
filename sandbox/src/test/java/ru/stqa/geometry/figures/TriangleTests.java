package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TriangleTests {


   @Test
    void canCreateTriangle() {
        try {
            // here we can change values of sides to check triangle inequality. E.g. new Triangle(2.0, 3.0, 5.0);
            new Triangle(2.0, 3.0,  5.0);
        } catch(IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Test
    void canCalculatePerimeter() {
        var trn = new Triangle(2.0, 3.0, 4.0);
        Assertions.assertEquals(9.0, trn.perimeter());
    }

    @Test
    void canCalculateArea() {
        var trn = new Triangle(2.0, 3.0, 4.0);
        // Asserts that expected and actual are equal within the given delta.
        Assertions.assertEquals(2.905, trn.area(), 0.001);
    }

    @Test
    void testEquality() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(2.0, 3.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }

    //
    public static int compareTriangles(int testN, Triangle t1, double a, double b, double c, boolean expectedRes) {
        System.out.format("Start test %x. Triangle: %f, %f, %f. Expected equality is %b. ", testN, a, b, c, expectedRes);
        var t2 = new Triangle(a, b, c);
        //Assertions.assertEquals(t1, t2);
        boolean compRes = t1.equals(t2);
        if (compRes == expectedRes) {
            System.out.println("Passed!");
        }else{
            System.out.println("Failed!");
        }

        testN = testN + 1;
        return testN;
    }
    @Test
    void testEquality2() {
       // create sample triangle
        var a = 2.0;
        var b = 3.0;
        var c = 4.0;
        var t1 = new Triangle(a, b, c);
        // compare with other triangles
        var testN = 1;
        boolean expectedRes = true;
        testN = compareTriangles(testN, t1, 2.0, 3.0, 4.0, expectedRes);
        testN = compareTriangles(testN, t1, 2.0, 4.0, 3.0, expectedRes);
        testN = compareTriangles(testN, t1, 3.0, 2.0, 4.0, expectedRes);
        testN = compareTriangles(testN, t1, 3.0, 4.0, 2.0, expectedRes);
        testN = compareTriangles(testN, t1, 4.0, 2.0, 3.0, expectedRes);
        testN = compareTriangles(testN, t1, 4.0, 3.0, 2.0, expectedRes);
        testN = compareTriangles(testN, t1, 3.0, 3.0, 5.0, expectedRes); // failed test
        expectedRes = false;
        testN = compareTriangles(testN, t1, 3.0, 3.0, 5.0, expectedRes);

    }


}
