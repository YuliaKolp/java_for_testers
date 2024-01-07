package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(7.0);
        Square.printSquareArea(5);

        Rectangle.printRectangleArea(1.0, 2.0);
        Rectangle.printRectangleArea(2.0, 4.0);
        Rectangle.printRectangleArea(7.5,8.6);

    }

}
