package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Pawn {
    private String color;
    private ImageButton[][] board;
    private Figure[][] figBoard;
    private int position;

    public Pawn(String color, ImageButton[][] board, Figure[][] figBoard, int position) {
        this.color = color;
        this.board = board;
        this.figBoard = figBoard;
        this.position = position;
    }

    public void showOptions() {
        int x = this.position / 10;
        int y = this.position % 10;

        if (this.color.equals("white")) {
            //beneath the pawn (only moves)
            if(x<7) {
                if (this.figBoard[x + 1][y].getShape().equals("none")) {
                    board[x + 1][y].setBackgroundResource(R.color.green);
                    board[x + 1][y].setTag("possibleMove");
                }
            }

            //on the sides (only kills)
            if (x<7 && y<7) {
                if (this.figBoard[x + 1][y + 1].getColor().equals("black")){
                    board[x + 1][y + 1].setBackgroundResource(R.color.red);
                    this.board[x + 1][y + 1].setTag("possibleKill");
                }
            }

            if(x<7 && y>0) {
                if (this.figBoard[x + 1][y - 1].getColor().equals("black")){
                    board[x + 1][y - 1].setBackgroundResource(R.color.red);
                    this.board[x + 1][y - 1].setTag("possibleKill");
                }
            }

        } else if (this.color.equals("black")) {
            //above the pawn (only moves)
            if (x > 0) {
                if (this.figBoard[x - 1][y].getShape().equals("none")) {
                    board[x - 1][y].setBackgroundResource(R.color.green);
                    board[x - 1][y].setTag("possibleMove");
                }
            }

            //on the sides (only kills)
            if(x>0 && y<7) {
                if (this.figBoard[x-1][y + 1].getColor().equals("white")){
                    board[x - 1][y + 1].setBackgroundResource(R.color.red);
                    this.board[x - 1][y + 1].setTag("possibleKill");
                }
            }
            if(x>0 && y>0) {
                if (this.figBoard[x - 1][y - 1].getColor().equals("white")){
                    board[x - 1][y - 1].setBackgroundResource(R.color.red);
                    this.board[x - 1][y - 1].setTag("possibleKill");
                }
            }
        }
    }
}

