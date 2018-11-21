package com.yulia.milich.chess;

import android.widget.ImageButton;

public class King extends Figure{
    public King(String color, int position, int imageResource) {
        super(color, position, imageResource);
    }

    @Override
    public String getShape() {
        return "king";
    }

    public void move(Figure[][] figBoard, boolean[][] board, String[][] move){
        int x = this.position / 10;
        int y = this.position % 10;

        if (x>0) {
            if (!board[x - 1][y]) {
                move[x - 1][y] = "possible move";
            } else {
                if (!figBoard[x - 1][y].getColor().equals(this.color)) {
                    move[x - 1][y] = "possible kill";
                }
            }
        }

        if (x<7) {
            if (!board[x + 1][y]) {
                move[x + 1][y] = "possible move";
            } else {
                if (!figBoard[x + 1][y].getColor().equals(this.color)) {
                    move[x + 1][y] = "possible kill";
                }
            }
        }

        if (y>0) {
            if (!board[x][y - 1]) {
                move[x][y - 1] = "possible move";
            } else {
                if (!figBoard[x][y - 1].getColor().equals(this.color)) {
                    move[x][y - 1] = "possible kill";
                }
            }
        }

        if (y<7) {
            if (!board[x][y + 1]) {
                move[x][y + 1] = "possible move";
            } else {
                if (!figBoard[x][y + 1].getColor().equals(this.color)) {
                    move[x][y + 1] = "possible kill";
                }
            }
        }

        if (x<7 && y<7) {
            if (!board[x + 1][y + 1]) {
                move[x + 1][y + 1] = "possible move";
            } else {
                if (!figBoard[x + 1][y + 1].getColor().equals(this.color)) {
                    move[x + 1][y + 1] = "possible kill";
                }
            }
        }

        if (x>0 && y<7) {
            if (!board[x - 1][y + 1]) {
                move[x - 1][y + 1] = "possible move";
            } else {
                if (!figBoard[x - 1][y + 1].getColor().equals(this.color)) {
                    move[x - 1][y + 1] = "possible kill";
                }
            }
        }

        if (x<7 && y>0) {
            if (!board[x + 1][y - 1]) {
                move[x + 1][y - 1] = "possible move";
            } else {
                if (!figBoard[x + 1][y - 1].getColor().equals(this.color)) {
                    move[x + 1][y - 1] = "possible kill";
                }
            }
        }

        if (x>0 && y>0) {
            if (!board[x - 1][y - 1]) {
                move[x - 1][y - 1] = "possible move";
            } else {
                if (!figBoard[x - 1][y - 1].getColor().equals(this.color)) {
                    move[x - 1][y - 1] = "possible kill";
                }
            }
        }
    }
}
