package PersegiPanjang;

public class Square {
    private double lenght;
    private double width;
    private double area;
    private String color;

    public Square(){ //constructor default
        lenght= 2;
        width = 3;
        color = "blue";
    }

    public Square(double l, double w){ //constructor adjust lenght & width
        lenght = l;     //call the variable length without using 'this.' keyword
        width = w;
        color = "blue";
    }

    public Square(double lenght, double width, String color){ //constructor adjust lenght , width & color
        this.lenght = lenght;   //call the variable length using 'this.' keyword
        this.width = width;
        this.color = color;
    }
    // Setter
    public void setArea(){
        this.area =  lenght * width;
    }

    public void setColor(String color){
        this.color = color;
    }    
    // Getter
    public double getLenght(){
        return lenght;
    }

    public double getWidth(){
        return width;
    }

    public double getArea(){
        return area;
    }

    public String getColor(){
        return color;
    }

    @Override //toString() method
    public String toString(){
            return "[lenght=" + lenght + ", width=" + width + ", area=" + area + ", color=" + color + "]";
        };
}
