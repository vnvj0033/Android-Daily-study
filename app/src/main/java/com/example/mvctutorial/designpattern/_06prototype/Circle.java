package com.example.mvctutorial.designpattern._06prototype;

public class Circle extends Shape{


    int x, y, r;

    Circle(int x, int y, int r){
        this.x = x;
        this.y = y;
        this.r = r;
    }


    public Circle copy() throws CloneNotSupportedException {

        Circle circle = (Circle) clone();

        circle.x += 1;
        circle.y += 1;

        return circle;
    }
}
