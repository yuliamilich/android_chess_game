package com.yulia.milich.chess;

import android.widget.ImageButton;

public class King {
    private String color;
    private ImageButton[][] board;
    private Figure[][] figBoard;
    private int position;

    public King(String color, ImageButton[][] board, Figure[][] figBoard, int position) {
        this.color = color;
        this.board = board;
        this.figBoard = figBoard;
        this.position = position;
    }

    public void showOptions() {
        int x = this.position / 10;
        int y = this.position % 10;

        // above the king
        if (x>0) {
            if (this.figBoard[x - 1][y].getShape().equals("none")) {
                board[x - 1][y].setBackgroundResource(R.color.green);
                board[x - 1][y].setTag("possibleMove");
            } else {
                if (!this.figBoard[x - 1][y].getColor().equals(this.color)) {
                    board[x - 1][y].setBackgroundResource(R.color.red);
                    this.board[x - 1][y].setTag("possibleKill");
                }
            }
        }

        // beneath the king
        if(x<7) {
            if (this.figBoard[x + 1][y].getShape().equals("none")) {
                board[x + 1][y].setBackgroundResource(R.color.green);
                board[x + 1][y].setTag("possibleMove");
            } else {
                if (!this.figBoard[x + 1][y].getColor().equals(this.color)) {
                    board[x + 1][y].setBackgroundResource(R.color.red);
                    this.board[x + 1][y].setTag("possibleKill");
                }
            }
        }

        // on the right (on my phone)
        if(y>0) {
            if (this.figBoard[x][y - 1].getShape().equals("none")) {
                board[x][y - 1].setBackgroundResource(R.color.green);
                board[x][y - 1].setTag("possibleMove");
            } else {
                if (!this.figBoard[x][y - 1].getColor().equals(this.color)) {
                    board[x][y - 1].setBackgroundResource(R.color.red);
                    this.board[x][y - 1].setTag("possibleKill");
                }
            }
        }

        // on the left
        if(y<7) {
            if (this.figBoard[x][y + 1].getShape().equals("none")) {
                board[x][y + 1].setBackgroundResource(R.color.green);
                board[x][y + 1].setTag("possibleMove");
            } else {
                if (!this.figBoard[x][y + 1].getColor().equals(this.color)) {
                    board[x][y + 1].setBackgroundResource(R.color.red);
                    this.board[x][y + 1].setTag("possibleKill");
                }
            }
        }


        // dawn and left
        if (x<7 && y<7) {
            if (this.figBoard[x + 1][y + 1].getShape().equals("none")) {
                board[x + 1][y + 1].setBackgroundResource(R.color.green);
                board[x + 1][y + 1].setTag("possibleMove");
            } else {
                if (!this.figBoard[x + 1][y + 1].getColor().equals(this.color)) {
                    board[x + 1][y + 1].setBackgroundResource(R.color.red);
                    this.board[x + 1][y + 1].setTag("possibleKill");
                }
            }
        }

        //
        if(x>0 && y<7) {
            if (this.figBoard[x - 1][y + 1].getShape().equals("none")) {
                board[x - 1][y + 1].setBackgroundResource(R.color.green);
                board[x - 1][y + 1].setTag("possibleMove");
            } else {
                if (!this.figBoard[x - 1][y + 1].getColor().equals(this.color)) {
                    board[x - 1][y + 1].setBackgroundResource(R.color.red);
                    this.board[x - 1][y + 1].setTag("possibleKill");
                }
            }
        }

        if(x<7 && y>0) {
            if (this.figBoard[x + 1][y - 1].getShape().equals("none")) {
                board[x + 1][y - 1].setBackgroundResource(R.color.green);
                board[x + 1][y - 1].setTag("possibleMove");
            } else {
                if (!this.figBoard[x + 1][y - 1].getColor().equals(this.color)) {
                    board[x + 1][y - 1].setBackgroundResource(R.color.red);
                    this.board[x + 1][y - 1].setTag("possibleKill");
                }
            }
        }

        if(x>0 && y>0) {
            if (this.figBoard[x - 1][y - 1].getShape().equals("none")) {
                board[x - 1][y - 1].setBackgroundResource(R.color.green);
                board[x - 1][y - 1].setTag("possibleMove");
            } else {
                if (!this.figBoard[x - 1][y - 1].getColor().equals(this.color)) {
                    board[x - 1][y - 1].setBackgroundResource(R.color.red);
                    this.board[x - 1][y - 1].setTag("possibleKill");
                }
            }
        }
    }
}
