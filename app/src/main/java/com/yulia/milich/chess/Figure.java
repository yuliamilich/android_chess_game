package com.yulia.milich.chess;

public class Figure {
    private String shape;
    private String color;
    private int imageResource;

    public Figure(String shape, String color, int imageResource){
        this.shape = shape;
        this.color = color;
        this.imageResource = imageResource;
    }

    public String getShape(){
        return this.shape;
    }

    public String getColor(){
        return this.color;
    }

    public int getImageResource(){
        return this.imageResource;
    }

    public void setShape(String shape){
        this.shape = shape;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setImageResource(int imageResource){
        this.imageResource = imageResource;
    }

    public void copy(Figure figure) {
        this.color = figure.getColor();
        this.shape = figure.getShape();
        this.imageResource = figure.getImageResource();
    }
}
