public class Geometry {
    public static void main(String[] args) {
        printSquareArea(7.0);
        printSquareArea(5);

        printRectangleArea(1.0, 2.0);
        printRectangleArea(2.0, 4.0);
        printRectangleArea(7.5,8.6);

    }

    private static void printRectangleArea(double a, double b) {
        System.out.println("Area of a rectangular with sides " +  a + " and " + b + " = "  + rectangleArea(a, b));
    }

    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSquareArea(double side) {
        System.out.println("Area of a square with side " +  side + " = " + squareArea(side));
    }

    private static double squareArea(double a) {
        return a * a;
    }
}
