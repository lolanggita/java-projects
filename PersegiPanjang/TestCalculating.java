package PersegiPanjang;

public class TestCalculating {
    public static void main(String[] args) {
        System.out.println("'The detail of each Square'");
        Square s1 = new Square();
        System.out.println("Square 's1'; Length = " + s1.getLenght() + ", Width = " + s1.getWidth() + ", Color = " + s1.getColor() );

        Square s2 = new Square(7,9.5);
        System.out.println("Square 's2'; Length = " + s2.getLenght() + ", Width = " + s2.getWidth() + ", Color = " + s2.getColor() );

        Square s3 = new Square(4,2.5,"yellow");
        System.out.println("Square 's3'; Length = " + s3.getLenght() + ", Width = " + s3.getWidth() + ", Color = " + s3.getColor() );

        s2.setColor("red");
        System.out.println("s2 Square color is " + s2.getColor() + " //trying the setColor() method");
        System.out.println(""); //give some space between

        s1.setArea();
        s2.setArea();
        s3.setArea();
        System.out.println("'The Area of Square'");
        System.out.println("Area of s1 = " + s1.getArea());
        System.out.println("Area of s2 = " + s2.getArea());
        System.out.println("Area of s3 = " + s3.getArea());

        System.out.println(""); //give some space between
        System.out.println("'toString() Method....'");
        System.out.println("Square 1 " + s1.toString());
        System.out.println("Square 2 " + s2.toString()); // java implicitly called the toString() method from object 's2'
        System.out.println("Square 3 " + s3.toString());

    }
    
}
