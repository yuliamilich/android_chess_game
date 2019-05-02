package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Knight extends Figure{

    public Knight(String color, int position, int imageResource) {
        super(color, position, imageResource);
    }

    @Override
    public String getShape() {
        return "knight";
    }

    // marks the squares where the knight can move
    public void move(Figure[][] figBoard, boolean[][] board, String[][] move){
        int x = this.position / 10;
        int y = this.position % 10;

        if (x>1 && y>0) {
            if (!board[x - 2][y-1]) {
                move[x - 2][y-1] = "possible move";
            } else {
                if (!figBoard[x - 2][y-1].getColor().equals(this.color)) {
                    move[x - 2][y-1] = "possible kill";
                }
            }
        }

        if (x>1 && y<7) {
            if (!board[x - 2][y + 1]) {
                move[x - 2][y + 1] = "possible move";
            } else {
                if (!figBoard[x - 2][y + 1].getColor().equals(this.color)) {
                    move[x - 2][y + 1] = "possible kill";
                }
            }
        }

        if (x>0 && y>1) {
            if (!board[x - 1][y - 2]) {
                move[x - 1][y - 2] = "possible move";
            } else {
                if (!figBoard[x - 1][y - 2].getColor().equals(this.color)) {
                    move[x - 1][y - 2] = "possible kill";
                }
            }
        }

        if (x>0 && y<6) {
            if (!board[x - 1][y + 2]) {
                move[x - 1][y + 2] = "possible move";
            } else {
                if (!figBoard[x - 1][y + 2].getColor().equals(this.color)) {
                    move[x - 1][y + 2] = "possible kill";
                }
            }
        }

        if (x<7 && y>1) {
            if (!board[x + 1][y - 2]) {
                move[x + 1][y - 2] = "possible move";
            } else {
                if (!figBoard[x + 1][y - 2].getColor().equals(this.color)) {
                    move[x + 1][y - 2] = "possible kill";
                }
            }
        }

        if (x<7 && y<6) {
            if (!board[x + 1][y + 2]) {
                move[x + 1][y + 2] = "possible move";
            } else {
                if (!figBoard[x + 1][y + 2].getColor().equals(this.color)) {
                    move[x + 1][y + 2] = "possible kill";
                }
            }
        }

        if (x<6 && y>0) {
            if (!board[x + 2][y - 1]) {
                move[x + 2][y - 1] = "possible move";
            } else {
                if (!figBoard[x + 2][y - 1].getColor().equals(this.color)) {
                    move[x + 2][y - 1] = "possible kill";
                }
            }
        }

        if (x<6 && y<7) {
            if (!board[x + 2][y + 1]) {
                move[x + 2][y + 1] = "possible move";
            } else {
                if (!figBoard[x + 2][y + 1].getColor().equals(this.color)) {
                    move[x + 2][y + 1] = "possible kill";
                }
            }
        }
    }
}
