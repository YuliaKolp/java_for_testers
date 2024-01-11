package ru.stqa.geometry.figures;

public record Triangle(double a, double b, double c) {
    public Triangle{
        //System.out.println(String.format("---- Start to create triangle with sides '%f', '%f', '%f'.", a, b, c));
        var hintMessage = String.format("Check sides '%f', '%f', '%f'.", a, b, c);

        if ((a < 0) || (b < 0) || (c <0)) {
            var text = "Triangle side cannot be negative! " + hintMessage;
            throw new IllegalArgumentException(text);
        }

        if ((a >= (b + c)) || (b >= (c + a)) || (c >= (a + b))) {
            var text = "Triangle inequality is false! " + hintMessage;
            throw new IllegalArgumentException(text);
        }

    }
    public double perimeter () {
        var p = 0.0;
        p = a + b + c;
        return p;
    }

    public double area () {
        var p = perimeter() * 0.5;
        var trArea = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return trArea;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Triangle triangle = (Triangle) obj;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.c) == 0)
                || (Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.a) == 0)
                || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0)
                || (Double.compare(this.a, triangle.c) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.a) == 0)
                || (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.c) == 0 && Double.compare(this.c, triangle.b) == 0);
    }
}