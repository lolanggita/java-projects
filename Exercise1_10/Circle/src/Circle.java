package Exercise1_10.Circle.src;

public class Circle {
    private double radius;
    private String color;

    public Circle(){
        radius = 1.0;
        color = "red";
    }

    public Circle(double r){
        radius = r;
        color = "red";
    }

    public Circle(double radius, String color){
        this.radius = radius;
        this.color = color;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public void setColor(String color){
        this.color = color;
    } 

    public double getRadius(){
        return radius;
    }

    public String getColor(){
        return color;
    }
    
    public double getArea(){
        return radius*radius*Math.PI;
    }

    @Override //toString() method
    public String toString(){
            return "[radius=" + radius + ", area=" + getArea() + ", color=" + color + "]";
        };

}
