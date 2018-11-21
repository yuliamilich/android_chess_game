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

    public void move(Figure[][] figBoard, boolean[][] board, String[][] move) {
        int x = this.position / 10;
        int y = this.position % 10;

        if (x > 0) {
            if (!board[x - 1][y]) {
                move[x - 1][y] = "possible move";
            } else {
                if (!figBoard[x - 1][y].getColor().equals(this.color)) {
                    move[x - 1][y] = "possible kill";
                }
            }
        }

        if (x < 7) {
            if (!board[x + 1][y]) {
                move[x + 1][y] = "possible move";
            } else {
                if (!figBoard[x + 1][y].getColor().equals(this.color)) {
                    move[x + 1][y] = "possible kill";
                }
            }
        }

        if (y > 0) {
            if (!board[x][y - 1]) {
                move[x][y - 1] = "possible move";
            } else {
                if (!figBoard[x][y - 1].getColor().equals(this.color)) {
                    move[x][y - 1] = "possible kill";
                }
            }
        }

        if (y < 7) {
            if (!board[x][y + 1]) {
                move[x][y + 1] = "possible move";
            } else {
                if (!figBoard[x][y + 1].getColor().equals(this.color)) {
                    move[x][y + 1] = "possible kill";
                }
            }
        }

        if (x < 7 && y < 7) {
            if (!board[x + 1][y + 1]) {
                move[x + 1][y + 1] = "possible move";
            } else {
                if (!figBoard[x + 1][y + 1].getColor().equals(this.color)) {
                    move[x + 1][y + 1] = "possible kill";
                }
            }
        }

        if (x > 0 && y < 7) {
            if (!board[x - 1][y + 1]) {
                move[x - 1][y + 1] = "possible move";
            } else {
                if (!figBoard[x - 1][y + 1].getColor().equals(this.color)) {
                    move[x - 1][y + 1] = "possible kill";
                }
            }
        }

        if (x < 7 && y > 0) {
            if (!board[x + 1][y - 1]) {
                move[x + 1][y - 1] = "possible move";
            } else {
                if (!figBoard[x + 1][y - 1].getColor().equals(this.color)) {
                    move[x + 1][y - 1] = "possible kill";
                }
            }
        }

        if (x > 0 && y > 0) {
            if (!board[x - 1][y - 1]) {
                move[x - 1][y - 1] = "possible move";
            } else {
                if (!figBoard[x - 1][y - 1].getColor().equals(this.color)) {
                    move[x - 1][y - 1] = "possible kill";
                }
            }
        }
    }

    public boolean isCheck(Figure[][] figBoard, boolean[][] board, String[][] move) {
        int x = this.position / 10;
        int y = this.position % 10;
        boolean pieceInTheWay = false;

        boolean check = false;


        //threatened by a king
        // above the king
        if (x > 0) {
            if (board[x - 1][y]) {
                if (figBoard[x - 1][y].getShape().equals("king") && !figBoard[x - 1][y].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }

        // beneath the king
        if (x < 7) {
            if (board[x + 1][y]) {
                if (figBoard[x + 1][y].getShape().equals("king") && !figBoard[x + 1][y].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }

        // on the right (on my phone)
        if (y > 0) {
            if (board[x][y - 1]) {
                if (figBoard[x][y - 1].getShape().equals("king") && !figBoard[x][y - 1].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }

        // on the left
        if (y < 7) {
            if (board[x][y + 1]) {
                if (figBoard[x][y + 1].getShape().equals("king") && !figBoard[x][y + 1].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }


        // dawn and left
        if (x < 7 && y < 7) {
            if (board[x + 1][y + 1]) {
                if (figBoard[x + 1][y + 1].getShape().equals("king") && (!figBoard[x + 1][y + 1].getColor().equals(this.color))) {
                    check = true;
                }
            }
        }

        //
        if (x > 0 && y < 7) {
            if (board[x - 1][y + 1]) {
                if (figBoard[x - 1][y + 1].getShape().equals("king") && !figBoard[x - 1][y + 1].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }

        if (x < 7 && y > 0) {
            if (board[x + 1][y - 1]) {
                if (figBoard[x + 1][y - 1].getShape().equals("king") && !figBoard[x + 1][y - 1].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }

        if (x > 0 && y > 0) {
            if (board[x - 1][y - 1]) {
                if (figBoard[x - 1][y - 1].getShape().equals("king") && !figBoard[x - 1][y - 1].getColor().equals(this.color)) {
                    check = true;
                }
            }
        }

            // threatened by a rook or queen
            pieceInTheWay = false;
            for (int i = x - 1; i >= 0 && !pieceInTheWay; i--) {
                if (board[i][y]) {
                    pieceInTheWay = true;
                    if ((figBoard[i][y].getShape().equals("queen") || figBoard[i][y].getShape().equals("rook")) && !figBoard[i][y].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            pieceInTheWay = false;
            for (int i = x + 1; i < 8 && !pieceInTheWay; i++) {
                if (board[i][y]) {
                    pieceInTheWay = true;
                    if ((figBoard[i][y].getShape().equals("queen") || figBoard[i][y].getShape().equals("rook"))
                            && !figBoard[i][y].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }
            pieceInTheWay = false;
            for (int i = y - 1; i >= 0 && !pieceInTheWay; i--) {
                if (board[x][i]) {
                    pieceInTheWay = true;
                    if ((figBoard[x][i].getShape().equals("queen") || figBoard[x][i].getShape().equals("rook"))
                            && !figBoard[x][i].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }
            pieceInTheWay = false;
            for (int i = y + 1; i < 8 && !pieceInTheWay; i++) {
                if (board[x][i]) {
                    pieceInTheWay = true;
                    if ((figBoard[x][i].getShape().equals("queen") || figBoard[x][i].getShape().equals("rook"))
                            && !figBoard[x][i].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            // threatened by a bishop or queen
            pieceInTheWay = false;
            for (int i = 1; (x - i >= 0) && (y + i < 8) && !pieceInTheWay; i++) {
                if (board[x - i][y + i]) {
                    pieceInTheWay = true;
                    if ((figBoard[x - i][y + i].getShape().equals("queen") || figBoard[x - i][y + i].getShape().equals("bishop"))
                            && !figBoard[x - i][y + i].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            pieceInTheWay = false;
            for (int i = 1; (x + i < 8) && (y + i < 8) && !pieceInTheWay; i++) {
                if (board[x + i][y + i]) {
                    pieceInTheWay = true;
                    if ((figBoard[x + i][y + i].getShape().equals("queen") || figBoard[x + i][y + i].getShape().equals("bishop"))
                            && !figBoard[x + i][y + i].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }
            pieceInTheWay = false;
            for (int i = 1; (x - i >= 0) && (y - i >= 0) && !pieceInTheWay; i++) {
                if (board[x - i][y - i]) {
                    pieceInTheWay = true;
                    if ((figBoard[x - i][y - i].getShape().equals("queen") || figBoard[x - i][y - i].getShape().equals("bishop"))
                            && !figBoard[x - i][y - i].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }
            pieceInTheWay = false;
            for (int i = 1; (x + i < 8) && (y - i >= 0) && !pieceInTheWay; i++) {
                if (board[x + i][y - i]) {
                    pieceInTheWay = true;
                    if ((figBoard[x + i][y - i].getShape().equals("queen") || figBoard[x + i][y - i].getShape().equals("bishop"))
                            && !figBoard[x + i][y - i].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            //threatened by knight
            if (x > 1 && y > 0) {
                if(board[x - 2][y - 1]) {
                    if (figBoard[x - 2][y - 1].getShape().equals("knight") && !figBoard[x - 2][y - 1].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            // beneath the king
            if (x > 1 && y < 7) {
                if(board[x - 2][y + 1]) {
                    if (figBoard[x - 2][y + 1].getShape().equals("knight") && !figBoard[x - 2][y + 1].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            // on the right (on my phone)
            if (x > 0 && y > 1) {
                if(board[x - 1][y - 2]) {
                    if (figBoard[x - 1][y - 2].getShape().equals("knight") && !figBoard[x - 1][y - 2].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            // on the left
            if (x > 0 && y < 6) {
                if(board[x - 1][y + 2]) {
                    if (figBoard[x - 1][y + 2].getShape().equals("knight") && !figBoard[x - 1][y + 2].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }


            // dawn and left
            if (x < 7 && y > 1) {
                if(board[x + 1][y - 2]) {
                    if (figBoard[x + 1][y - 2].getShape().equals("knight") && !figBoard[x + 1][y - 2].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            //
            if (x < 7 && y < 6) {
                if(board[x + 1][y + 2]) {
                    if (figBoard[x + 1][y + 2].getShape().equals("knight") && !figBoard[x + 1][y + 2].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            if (x < 6 && y > 0) {
                if(board[x + 2][y - 1]) {
                    if (figBoard[x + 2][y - 1].getShape().equals("knight") && !figBoard[x + 2][y - 1].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

            if (x < 6 && y < 7) {
                if(board[x + 2][y + 1]) {
                    if (figBoard[x + 2][y + 1].getShape().equals("knight") && !figBoard[x + 2][y + 1].getColor().equals(this.color)) {
                        check = true;
                    }
                }
            }

//        if (x<7 && y<7) {
//            if (this.figBoard[x + 1][y + 1].getShape().equals("king") && (!this.figBoard[x + 1][y + 1].getColor().equals(this.color))) {
//                check = true;
//            }
//        }

            //pawn
            if (this.color.equals("white")) {
                //on the sides (only kills)
                if (x < 7 && y < 7) {
                    if(board[x + 1][y + 1]) {
                        if (figBoard[x + 1][y + 1].getColor().equals("black") && figBoard[x + 1][y + 1].getShape().equals("pawn")) {
                            check = true;
                        }
                    }
                }

                if (x < 7 && y > 0) {
                    if(board[x + 1][y - 1]) {
                        if (figBoard[x + 1][y - 1].getColor().equals("black") && figBoard[x + 1][y - 1].getShape().equals("pawn")) {
                            check = true;
                        }
                    }
                }
            } else {
                if (this.color.equals("black")) {
                    //on the sides (only kills)
                    if (x > 0 && y < 7) {
                        if(board[x - 1][y + 1]) {
                            if (figBoard[x - 1][y + 1].getColor().equals("white") && figBoard[x - 1][y + 1].getShape().equals("pawn")) {
                                check = true;
                            }
                        }
                    }

                    if (x > 0 && y > 0) {
                        if(board[x - 1][y - 1]) {
                            if (figBoard[x - 1][y - 1].getColor().equals("white") && figBoard[x - 1][y - 1].getShape().equals("pawn")) {
                                check = true;
                            }
                        }
                    }
                }
            }


            return check;
    }
}
