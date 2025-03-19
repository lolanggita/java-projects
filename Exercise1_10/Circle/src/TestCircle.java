package Exercise1_10.Circle.src;

public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println("The circle has radius of " + c1.getRadius() + " and area of " + c1.getArea() + " with color " + c1.getColor());

        Circle c2 = new Circle(2.0);
        System.out.println("The circle has radius of " + c2.getRadius() + " and area of " + c2.getArea() + " with color " + c2.getColor());

        Circle c3 = new Circle(4.8, "green");
        System.out.println("The circle has radius of " + c3.getRadius() + " and area of " + c3.getArea() + " with color " + c3.getColor());

        System.out.println("");
        System.out.println("The area of circle 1 = " + c1.getArea());
        System.out.println("The area of circle 2 = " + c2.getArea());
        System.out.println("The area of circle 3 = " + c3.getArea());

        c2.setRadius(123.4);
        c2.setColor("cyan");
        System.out.println("The area of circle 2 if the radius is 123.4 = " + c2.getArea());
        System.out.println("The color of circle 2 after we set a new one is  " + c2.getColor());

        System.out.println("");
        c1.toString();
        c2.toString();
        c3.toString();
        System.out.println("Circle 1 " + c1.toString());
        System.out.println("Circle 2 " + c2.toString()); // java implicitly called the toString() method from each object 
        System.out.println("Circle 3 " + c3.toString());
    }
}
