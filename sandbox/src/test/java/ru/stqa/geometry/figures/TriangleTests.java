package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void isTriangle() {
        var a = 2.0;
        var b = 3.0;
        var c = 4.0;
        var t = new Triangle(a, b, c);
        Assertions.assertTrue((a < b + c) & (b < c + a) & (c < a + b));
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(9.0, new Triangle(2.0, 3.0, 4.0).perimeter());
    }

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(2.9047375096555625, new Triangle(2.0, 3.0, 4.0).area());
    }

}
