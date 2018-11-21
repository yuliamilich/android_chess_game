package com.yulia.milich.chess;

import android.widget.ImageButton;

public class King extends Figure {

    public King(String color, int position, int imageResource) {
        super(color, position, imageResource);
    }

    @Override
    public String getShape() {
        return "king";
    }

//    public void showOptions() {
////        boolean pieceInTheWay = false;
//        int x = this.position / 10;
//        int y = this.position % 10;
//
//        // above the king
//        if (x>0) {
//            if (this.figBoard[x - 1][y].getShape().equals("none")) {
//                board[x - 1][y].setBackgroundResource(R.color.green);
//                board[x - 1][y].setTag("possibleMove");
//            } else {
//                if(this.figBoard[x - 1][y].getColor().equals(this.color)) {
//                    board[x - 1][y].setBackgroundResource(R.color.red);
//                    this.board[x - 1][y].setTag("possibleKill");
//                }
//            }
//        }
//
////        pieceInTheWay = false;
//
//        // beneath the king
//        if(x<7) {
//            if (this.figBoard[x + 1][y].getShape().equals("none")) {
//                board[x + 1][y].setBackgroundResource(R.color.green);
//                board[x + 1][y].setTag("possibleMove");
//            } else {
//                board[x + 1][y].setBackgroundResource(R.color.red);
//                this.board[x + 1][y].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//
////        pieceInTheWay = false;
//
//        // on the right (on my phone)
//        if(y>0) {
//            if (this.figBoard[x][y - 1].getShape().equals("none")) {
//                board[x][y - 1].setBackgroundResource(R.color.green);
//                board[x][y - 1].setTag("possibleMove");
//            } else {
//                board[x][y - 1].setBackgroundResource(R.color.red);
//                this.board[x][y - 1].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//
////        pieceInTheWay = false;
//
//
//        // on the left
//        if(y<7) {
//            if (this.figBoard[x][y + 1].getShape().equals("none")) {
//                board[x][y + 1].setBackgroundResource(R.color.green);
//                board[x][y + 1].setTag("possibleMove");
//            } else {
//                board[x][y + 1].setBackgroundResource(R.color.red);
//                this.board[x][y + 1].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//
//
//        // dawn and left
//        if (x<7 && y<7) {
//            if (this.figBoard[x + 1][y + 1].getShape().equals("none")) {
//                board[x + 1][y + 1].setBackgroundResource(R.color.green);
//                board[x + 1][y + 1].setTag("possibleMove");
//            } else {
//                board[x + 1][y + 1].setBackgroundResource(R.color.red);
//                this.board[x + 1][y + 1].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//
//        //
//        if(x>0 && y<7) {
//            if (this.figBoard[x - 1][y + 1].getShape().equals("none")) {
//                board[x - 1][y + 1].setBackgroundResource(R.color.green);
//                board[x - 1][y + 1].setTag("possibleMove");
//            } else {
//                board[x - 1][y + 1].setBackgroundResource(R.color.red);
//                this.board[x - 1][y + 1].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//
//        if(x<7 && y>0) {
//            if (this.figBoard[x + 1][y - 1].getShape().equals("none")) {
//                board[x + 1][y - 1].setBackgroundResource(R.color.green);
//                board[x + 1][y - 1].setTag("possibleMove");
//            } else {
//                board[x + 1][y - 1].setBackgroundResource(R.color.red);
//                this.board[x + 1][y - 1].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//
//        if(x>0 && y>0) {
//            if (this.figBoard[x - 1][y - 1].getShape().equals("none")) {
//                board[x - 1][y - 1].setBackgroundResource(R.color.green);
//                board[x - 1][y - 1].setTag("possibleMove");
//            } else {
//                board[x - 1][y - 1].setBackgroundResource(R.color.red);
//                this.board[x - 1][y - 1].setTag("possibleKill");
////            pieceInTheWay = true;
//            }
//        }
//    }

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
