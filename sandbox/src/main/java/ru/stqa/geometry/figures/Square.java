package ru.stqa.geometry.figures;

public record Square(double side) {

    public static void printSquareArea(Square s) {
        String text = String.format("Area of a square with side %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return 4 * this.side;
    }
}
