package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Knight {
    private String color;
    private ImageButton[][] board;
    private Figure[][] figBoard;
    private int position;

    public Knight(String color, ImageButton[][] board, Figure[][] figBoard, int position){
        this.color = color;
        this.board = board;
        this.figBoard = figBoard;
        this.position = position;
    }

    public void showOptions() {
//        boolean pieceInTheWay = false;
        int x = this.position / 10;
        int y = this.position % 10;

        // above the king
        if (x>1 && y>0) {
            if (this.figBoard[x - 2][y-1].getShape().equals("none")) {
                board[x - 2][y-1].setBackgroundResource(R.color.green);
                board[x - 2][y-1].setTag("possibleMove");
            } else {
                board[x - 2][y-1].setBackgroundResource(R.color.red);
                this.board[x - 2][y-1].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }

//        pieceInTheWay = false;

        // beneath the king
        if(x>1 && y<7) {
            if (this.figBoard[x - 2][y + 1].getShape().equals("none")) {
                board[x - 2][y + 1].setBackgroundResource(R.color.green);
                board[x - 2][y + 1].setTag("possibleMove");
            } else {
                board[x - 2][y + 1].setBackgroundResource(R.color.red);
                this.board[x - 2][y + 1].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }

//        pieceInTheWay = false;

        // on the right (on my phone)
        if(x>0 && y>1) {
            if (this.figBoard[x - 1][y - 2].getShape().equals("none")) {
                board[x - 1][y - 2].setBackgroundResource(R.color.green);
                board[x - 1][y - 2].setTag("possibleMove");
            } else {
                board[x - 1][y - 2].setBackgroundResource(R.color.red);
                this.board[x - 1][y - 2].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }

//        pieceInTheWay = false;


        // on the left
        if(x>0 && y<6) {
            if (this.figBoard[x - 1][y + 2].getShape().equals("none")) {
                board[x - 1][y + 2].setBackgroundResource(R.color.green);
                board[x - 1][y + 2].setTag("possibleMove");
            } else {
                board[x - 1][y + 2].setBackgroundResource(R.color.red);
                this.board[x - 1][y + 2].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }


        // dawn and left
        if (x<7 && y>1) {
            if (this.figBoard[x + 1][y - 2].getShape().equals("none")) {
                board[x + 1][y - 2].setBackgroundResource(R.color.green);
                board[x + 1][y - 2].setTag("possibleMove");
            } else {
                board[x + 1][y - 2].setBackgroundResource(R.color.red);
                this.board[x + 1][y - 2].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }

        //
        if(x<7 && y<6) {
            if (this.figBoard[x + 1][y + 2].getShape().equals("none")) {
                board[x + 1][y + 2].setBackgroundResource(R.color.green);
                board[x + 1][y + 2].setTag("possibleMove");
            } else {
                board[x + 1][y + 2].setBackgroundResource(R.color.red);
                this.board[x + 1][y + 2].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }

        if(x<6 && y>0) {
            if (this.figBoard[x + 2][y - 1].getShape().equals("none")) {
                board[x + 2][y - 1].setBackgroundResource(R.color.green);
                board[x + 2][y - 1].setTag("possibleMove");
            } else {
                board[x + 2][y - 1].setBackgroundResource(R.color.red);
                this.board[x + 2][y - 1].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }

        if(x<6 && y<7) {
            if (this.figBoard[x + 2][y + 1].getShape().equals("none")) {
                board[x + 2][y + 1].setBackgroundResource(R.color.green);
                board[x + 2][y + 1].setTag("possibleMove");
            } else {
                board[x + 2][y + 1].setBackgroundResource(R.color.red);
                this.board[x + 2][y + 1].setTag("possibleKill");
//            pieceInTheWay = true;
            }
        }
    }
}
