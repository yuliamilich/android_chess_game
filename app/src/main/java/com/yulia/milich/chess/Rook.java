package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Rook {
    private String color;
    //    private String theOtherColor;
    private ImageButton[][] board;
    private Figure[][] figBoard;
    private int position;

    public Rook(String color, ImageButton[][] board, Figure[][] figBoard, int position) {
        this.color = color;
//        if(color.equals("white")){
//            theOtherColor = "black";
//        }
//        else{
//            theOtherColor = "white";
//        }
        this.board = board;
        this.figBoard = figBoard;
        this.position = position;
    }

    public void showOptions() {
        boolean pieceInTheWay = false;
        int x = this.position / 10;
        int y = this.position % 10;
        for (int i = x - 1; i >= 0 && !pieceInTheWay; i--) {
            if (this.figBoard[i][y].getShape().equals("none")) {
                this.board[i][y].setBackgroundResource(R.color.green);
                this.board[i][y].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[i][y].getColor().equals(this.color)) {
                    this.board[i][y].setBackgroundResource(R.color.red);
                    this.board[i][y].setTag("possibleKill");
                }
            }
        }

        pieceInTheWay = false;
        for (int i = x + 1; i < 8 && !pieceInTheWay; i++) {
            if (this.figBoard[i][y].getShape().equals("none")) {
                this.board[i][y].setBackgroundResource(R.color.green);
                this.board[i][y].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[i][y].getColor().equals(this.color)) {
                    this.board[i][y].setBackgroundResource(R.color.red);
                    this.board[i][y].setTag("possibleKill");
                }
            }
        }
        pieceInTheWay = false;
        for (int i = y - 1; i >= 0 && !pieceInTheWay; i--) {
            if (this.figBoard[x][i].getShape().equals("none")) {
                this.board[x][i].setBackgroundResource(R.color.green);
                this.board[x][i].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[x][i].getColor().equals(this.color)) {
                    this.board[x][i].setBackgroundResource(R.color.red);
                    this.board[x][i].setTag("possibleKill");
                }
            }
        }
        pieceInTheWay = false;
        for (int i = y + 1; i < 8 && !pieceInTheWay; i++) {
            if (this.figBoard[x][i].getShape().equals("none")) {
                this.board[x][i].setBackgroundResource(R.color.green);
                this.board[x][i].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[x][i].getColor().equals(this.color)) {
                    this.board[x][i].setBackgroundResource(R.color.red);
                    this.board[x][i].setTag("possibleKill");
                }
            }
        }
    }
}
