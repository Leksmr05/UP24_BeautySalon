package PR2;
import java.util.*;
abstract class Quadrilateral {
    protected double x1, y1, x2, y2, x3, y3, x4, y4;
    public Quadrilateral(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
    }
    public abstract double area();
    public abstract double perimeter();
}
class Square extends Quadrilateral {
    private double side;
    public Square(double x1, double y1, double side) {
        super(x1, y1, x1 + side, y1, x1 + side, y1 + side, x1, y1 + side);
        this.side = side;
    }
    @Override
    public double area() {
        return side * side;
    }
    @Override
    public double perimeter() {
        return 4 * side;
    }
}
class Rectangle extends Quadrilateral {
    private double width, height;
    public Rectangle(double x1, double y1, double width, double height) {
        super(x1, y1, x1 + width, y1, x1 + width, y1 + height, x1, y1 + height);
        this.width = width;
        this.height = height;
    }
    @Override
    public double area() {
        return width * height;
    }
    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}
class Rhombus extends Quadrilateral {
    private double side, diag1, diag2;
    public Rhombus(double x1, double y1, double side, double diag1, double diag2) {
        super(x1, y1, x1 + diag1 / 2, y1 + diag2 / 2, x1, y1 + diag2, x1 - diag1 / 2, y1 + diag2 / 2);
        this.side = side;
        this.diag1 = diag1;
        this.diag2 = diag2;
    }
    @Override
    public double area() {
        return (diag1 * diag2) / 2;
    }
    @Override
    public double perimeter() {
        return 4 * side;
    }
}
class ArbitraryQuadrilateral extends Quadrilateral {
    public ArbitraryQuadrilateral(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        super(x1, y1, x2, y2, x3, y3, x4, y4);
    }
    @Override
    public double area() {
        return Math.abs((x1 * y2 + x2 * y3 + x3 * y4 + x4 * y1) - (y1 * x2 + y2 * x3 + y3 * x4 + y4 * x1)) / 2;
    }
    @Override
    public double perimeter() {
        return distance(x1, y1, x2, y2) + distance(x2, y2, x3, y3) + distance(x3, y3, x4, y4) + distance(x4, y4, x1, y1);
    }
    private double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
public class Main {
    public static void main(String[] args) {
        List<Quadrilateral> shapes = new ArrayList<>();
        shapes.add(new Square(0, 0, 5));
        shapes.add(new Rectangle(0, 0, 4, 6));
        shapes.add(new Rhombus(0, 0, 5, 8, 6));
        shapes.add(new ArbitraryQuadrilateral(0, 0, 4, 0, 4, 3, 0, 3));
        int squares = 0, rectangles = 0, rhombuses = 0, arbitrary = 0;
        for (Quadrilateral shape : shapes) {
            if (shape instanceof Square) squares++;
            else if (shape instanceof Rectangle) rectangles++;
            else if (shape instanceof Rhombus) rhombuses++;
            else if (shape instanceof ArbitraryQuadrilateral) arbitrary++;
        }
        System.out.println("Количество квадратов: " + squares);
        System.out.println("Количество прямоугольников: " + rectangles);
        System.out.println("Количество ромбов: " + rhombuses);
        System.out.println("Количество произвольных четырехугольников: " + arbitrary);
        findExtremes(shapes);
    }
    public static void findExtremes(List<Quadrilateral> shapes) {
        Quadrilateral maxAreaShape = null, minAreaShape = null;
        Quadrilateral maxPerimeterShape = null, minPerimeterShape = null;
        for (Quadrilateral shape : shapes) {
            if (maxAreaShape == null || shape.area() > maxAreaShape.area()) {
                maxAreaShape = shape;
            }
            if (minAreaShape == null || shape.area() < minAreaShape.area()) {
                minAreaShape = shape;
            }
            if (maxPerimeterShape == null || shape.perimeter() > maxPerimeterShape.perimeter()) {
                maxPerimeterShape = shape;
            }
            if (minPerimeterShape == null || shape.perimeter() < minPerimeterShape.perimeter()) {
                minPerimeterShape = shape;
            }
        }
        System.out.println("Наибольшая площадь: " + maxAreaShape.area());
        System.out.println("Наименьшая площадь: " + minAreaShape.area());
        System.out.println("Наибольший периметр: " + maxPerimeterShape.perimeter());
        System.out.println("Наименьший периметр: " + minPerimeterShape.perimeter());
    }
}
