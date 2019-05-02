package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Pawn extends Figure{
    public Pawn(String color, int position, int imageResource) {
        super(color, position, imageResource);
    }

    @Override
    public String getShape() {
        return "pawn";
    }

    // marks the squares where the pawn can move
    public void move(Figure[][] figBoard, boolean[][] board, String[][] move){
        int x = this.position / 10;
        int y = this.position % 10;

        if (this.color.equals("white")) {
            //beneath the pawn (only moves)
            if(x == 1)
            {
                if(!board[x + 1][y]){
                    if(!board[x + 2][y]){
                        move[x + 2][y] = "possible move";
                    }
                }
            }

            if(x<7) {
                if (!board[x + 1][y]) {
                    move[x + 1][y] = "possible move";
                }
            }

            //on the sides (only kills)
            if (x<7 && y<7) {
                if (board[x + 1][y + 1]) {
                    if (figBoard[x + 1][y + 1].getColor().equals("black")) {
                        move[x + 1][y + 1] = "possible kill";
                    }
                }
            }

            if(x<7 && y>0) {
                if (board[x + 1][y - 1]) {
                    if (figBoard[x + 1][y - 1].getColor().equals("black")) {
                        move[x + 1][y - 1] = "possible kill";
                    }
                }
            }

        } else if (this.color.equals("black")) {
            //above the pawn (only moves)
            if(x == 6)
            {
                if(!board[x - 1][y]){
                    if(!board[x - 2][y]){
                        move[x - 2][y] = "possible move";
                    }
                }
            }

            if (x > 0) {
                if (!board[x - 1][y]) {
                    move[x - 1][y] = "possible move";
                }
            }

            //on the sides (only kills)
            if(x>0 && y<7) {
                if (board[x - 1][y + 1]) {
                    if (figBoard[x - 1][y + 1].getColor().equals("white")) {
                        move[x - 1][y + 1] = "possible kill";
                    }
                }
            }
            if(x>0 && y>0) {
                if (board[x - 1][y - 1]) {
                    if (figBoard[x - 1][y - 1].getColor().equals("white")) {
                        move[x - 1][y - 1] = "possible kill";
                    }
                }
            }
        }

    }
}

