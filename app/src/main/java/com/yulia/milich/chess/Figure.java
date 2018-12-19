package com.yulia.milich.chess;

public abstract class Figure {
    //protected String shape;
    protected String color;
    protected int imageResource;
    protected int position;

    public Figure(String color, int position, int imageResource){
        this.color = color;
        this.position = position;
        this.imageResource = imageResource;
    }

    public int getImageResource(){
        return this.imageResource;
    }

    public String getColor(){
        return this.color;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public abstract String getShape();

    public abstract void move(Figure[][] figBoard, boolean[][] board, String[][] move);

}
