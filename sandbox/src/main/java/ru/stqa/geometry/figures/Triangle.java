package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {

    public double perimeter() {
        return a + b + c;
    }

    public double area() {
        var p = perimeter() * 0.5;
        var trArea =  Math.sqrt(p * (p - a) * (p - b) * (p - c));

        return trArea;
    }

}
