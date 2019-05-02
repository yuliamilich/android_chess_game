package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Bishop extends Figure{

    public Bishop(String color, int position, int imageResource) {
        super(color, position, imageResource);
    }

    @Override
    public String getShape() {
        return "bishop";
    }

    // marks the squares where the bishop can move
    public void move(Figure[][] figBoard, boolean[][] board, String[][] move){
        boolean pieceInTheWay = false;
        int x = this.position / 10;
        int y = this.position % 10;
        for (int i = 1; (x-i >= 0) && (y+i < 8) && !pieceInTheWay; i++) {
            if (!board[x-i][y+i]) {
                move[x-i][y+i] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[x-i][y+i].getColor().equals(this.color)) {
                    move[x-i][y+i] = "possible kill";
                }
            }
        }

        pieceInTheWay = false;
        for (int i = 1; (x+i < 8) && (y+i < 8) && !pieceInTheWay; i++) {
            if (!board[x + i][y + i]) {
                move[x + i][y + i] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[x + i][y + i].getColor().equals(this.color)) {
                    move[x + i][y + i] = "possible kill";
                }
            }
        }

        pieceInTheWay = false;
        for (int i = 1; (x-i >= 0) && (y-i >= 0) && !pieceInTheWay; i++) {
            if (!board[x-i][y-i]) {
                move[x-i][y-i] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[x-i][y-i].getColor().equals(this.color)) {
                    move[x-i][y-i] = "possible kill";
                }
            }
        }

        pieceInTheWay = false;
        for (int i = 1; (x+i < 8) && (y-i >= 0) && !pieceInTheWay; i++) {
            if (!board[x+i][y-i]) {
                move[x+i][y-i] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[x+i][y-i].getColor().equals(this.color)) {
                    move[x+i][y-i] = "possible kill";
                }
            }
        }

    }
}
