package com.yulia.milich.chess;

import android.widget.ImageButton;

public class Rook extends Figure{

    public Rook(String color, int position, int imageResource) {
        super(color, position, imageResource);
    }

    @Override
    public String getShape() {
        return "rook";
    }

//    public void showOptions() {
//        boolean pieceInTheWay = false;
//        int x = this.position / 10;
//        int y = this.position % 10;
//        for (int i = x - 1; i >= 0 && !pieceInTheWay; i--) {
//            if (this.figBoard[i][y].getShape().equals("none")) {
//                this.board[i][y].setBackgroundResource(R.color.green);
//                this.board[i][y].setTag("possibleMove");
//            } else {
//                pieceInTheWay = true;
//                if (!this.figBoard[i][y].getColor().equals(this.color)) {
//                    this.board[i][y].setBackgroundResource(R.color.red);
//                    this.board[i][y].setTag("possibleKill");
//                }
//            }
//        }
//
//        pieceInTheWay = false;
//        for (int i = x + 1; i < 8 && !pieceInTheWay; i++) {
//            if (this.figBoard[i][y].getShape().equals("none")) {
//                this.board[i][y].setBackgroundResource(R.color.green);
//                this.board[i][y].setTag("possibleMove");
//            } else {
//                pieceInTheWay = true;
//                if (!this.figBoard[i][y].getColor().equals(this.color)) {
//                    this.board[i][y].setBackgroundResource(R.color.red);
//                    this.board[i][y].setTag("possibleKill");
//                }
//            }
//        }
//        pieceInTheWay = false;
//        for (int i = y - 1; i >= 0 && !pieceInTheWay; i--) {
//            if (this.figBoard[x][i].getShape().equals("none")) {
//                this.board[x][i].setBackgroundResource(R.color.green);
//                this.board[x][i].setTag("possibleMove");
//            } else {
//                pieceInTheWay = true;
//                if (!this.figBoard[x][i].getColor().equals(this.color)) {
//                    this.board[x][i].setBackgroundResource(R.color.red);
//                    this.board[x][i].setTag("possibleKill");
//                }
//            }
//        }
//        pieceInTheWay = false;
//        for (int i = y + 1; i < 8 && !pieceInTheWay; i++) {
//            if (this.figBoard[x][i].getShape().equals("none")) {
//                this.board[x][i].setBackgroundResource(R.color.green);
//                this.board[x][i].setTag("possibleMove");
//            } else {
//                pieceInTheWay = true;
//                if (!this.figBoard[x][i].getColor().equals(this.color)) {
//                    this.board[x][i].setBackgroundResource(R.color.red);
//                    this.board[x][i].setTag("possibleKill");
//                }
//            }
//        }
//    }

    @Override
    public void move(Figure[][] figBoard, boolean[][] board, String[][] move){
        boolean pieceInTheWay = false;
        int x = this.position / 10;
        int y = this.position % 10;
        for (int i = x - 1; i >= 0 && !pieceInTheWay; i--) {
            if (!board[i][y]) {
                move[i][y] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[i][y].getColor().equals(this.color)) {
                    move[i][y] = "possible kill";
                }
            }
        }

        pieceInTheWay = false;
        for (int i = x + 1; i < 8 && !pieceInTheWay; i++) {
            if (!board[i][y]) {
                move[i][y] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[i][y].getColor().equals(this.color)) {
                    move[i][y] = "possible kill";
                }
            }
        }

        pieceInTheWay = false;
        for (int i = y - 1; i >= 0 && !pieceInTheWay; i--) {
            if (!board[x][i]) {
                move[x][i] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[x][i].getColor().equals(this.color)) {
                    move[x][i] = "possible kill";
                }
            }
        }

        pieceInTheWay = false;
        for (int i = y + 1; i < 8 && !pieceInTheWay; i++) {
            if (!board[x][i]) {
                move[x][i] = "possible move";
            } else {
                pieceInTheWay = true;
                if (!figBoard[x][i].getColor().equals(this.color)) {
                    move[x][i] = "possible kill";
                }
            }
        }

    }
}
