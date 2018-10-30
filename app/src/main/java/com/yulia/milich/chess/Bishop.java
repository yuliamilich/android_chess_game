package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Bishop {
    private String color;
    private ImageButton[][] board;
    private Figure[][] figBoard;
    private int position;

    public Bishop(String color, ImageButton[][] board, Figure[][] figBoard, int position) {
        this.color = color;
        this.board = board;
        this.figBoard = figBoard;
        this.position = position;
    }

    public void showOptions() {
        boolean pieceInTheWay = false;
        int x = this.position / 10;
        int y = this.position % 10;

        for (int i = 1; (x-i >= 0) && (y+i < 8) && !pieceInTheWay; i++) {
            if (this.figBoard[x-i][y+i].getShape().equals("none")) {
                this.board[x-i][y+i].setBackgroundResource(R.color.green);
                this.board[x-i][y+i].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[x-i][y+i].getColor().equals(this.color)) {
                    this.board[x-i][y+i].setBackgroundResource(R.color.red);
                    this.board[x-i][y+i].setTag("possibleKill");
                }
            }
        }

        pieceInTheWay = false;
        for (int i = 1; (x+i < 8) && (y+i < 8) && !pieceInTheWay; i++) {
            if (this.figBoard[x + i][y + i].getShape().equals("none")) {
                this.board[x + i][y + i].setBackgroundResource(R.color.green);
                this.board[x + i][y + i].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[x + i][y + i].getColor().equals(this.color)) {
                    this.board[x + i][y + i].setBackgroundResource(R.color.red);
                    this.board[x + i][y + i].setTag("possibleKill");
                }
            }
        }

        pieceInTheWay = false;
        for (int i = 1; (x-i >= 0) && (y-i >= 0) && !pieceInTheWay; i++) {
            if (this.figBoard[x-i][y-i].getShape().equals("none")) {
                this.board[x-i][y-i].setBackgroundResource(R.color.green);
                this.board[x-i][y-i].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[x-i][y-i].getColor().equals(this.color)) {
                    this.board[x-i][y-i].setBackgroundResource(R.color.red);
                    this.board[x-i][y-i].setTag("possibleKill");
                }
            }
        }
        pieceInTheWay = false;
        for (int i = 1; (x+i < 8) && (y-i >= 0) && !pieceInTheWay; i++) {
            if (this.figBoard[x+i][y-i].getShape().equals("none")) {
                this.board[x+i][y-i].setBackgroundResource(R.color.green);
                this.board[x+i][y-i].setTag("possibleMove");
            } else {
                pieceInTheWay = true;
                if (!this.figBoard[x+i][y-i].getColor().equals(this.color)) {
                    this.board[x+i][y-i].setBackgroundResource(R.color.red);
                    this.board[x+i][y-i].setTag("possibleKill");
                }
            }
        }
    }
}
