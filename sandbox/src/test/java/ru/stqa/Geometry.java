package ru.stqa;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry
{
    public static void main(String[] args){
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
        //var squares = Stream.of(new Square(7.0), new Square(5.0), new Square(3.0));
        Consumer<Square> print = square -> {
            Square.printSquareArea(square);
            Square.printSquarePerimeter(square);
        };
        squares.peek(Square::printSquareArea).forEach(Square::printSquarePerimeter);
        /*var squares = List.of(new Square(7.0), new Square(5.0), new Square(3.0));
        for (Square square : squares) {
            Square.printSquareArea(square);
        }
        System.out.println("Functional----------------");
        /*Consumer<Square> print = (square) -> {
            Square.printSquareArea(square);
        };
        squares.forEach(print);*/
        //Consumer<Square> print = Square::printSquareArea;
        /*squares.forEach(Square::printSquareArea);*/

    }
}
