package com.yulia.milich.chess;

public abstract class Shape {
    private String color;
    private int position;

    public abstract Shape[][] move(Shape[][] figBoard);
}
