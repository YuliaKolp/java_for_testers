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

    @Test
    void testEquality2() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(2.0, 4.0, 3.0);
        Assertions.assertEquals(t1, t2);
   }

    @Test
    void testEquality3() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle( 3.0, 2.0, 4.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality4() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(3.0, 4.0, 2.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testEquality5() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(4.0, 2.0, 3.0);
        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testEquality6() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(4.0, 3.0, 2.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void testNonEquality() {
        var t1 = new Triangle(2.0, 3.0, 4.0);
        var t2 = new Triangle(3.0, 3.0, 5.0);
        Assertions.assertNotEquals(t1, t2);
    }

}
