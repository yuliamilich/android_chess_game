package com.yulia.milich.chess;


import android.annotation.SuppressLint;
import android.widget.ImageButton;

import java.util.ArrayList;


public class ChessManager {

    private TheChessGame gA;
    private Figure[][] figBoard = new Figure[8][8];
    //i don't know if it should be local or not
//    private Figure theMovedFigure = new Figure("none", "none", 0);

    //there is no actual use for these but i don't know....
    private ArrayList<Figure> fallenFiguresWhite;
    private ArrayList<Figure> fallenFiguresBlack;

    //i can just use the ones that above instead of these ints but it's more comfortable
    private int numberOfWhiteFallen;
    private int numberOfBlackFallen;

    private int whiteWon;
    private int blackWon;
    private int gamesPlayed;

    private int turn;
    private int beginner;

    //    private Figure kingWhite, kingBlack;
    private int kingWhite, kingBlack;

    public ChessManager(TheChessGame gA) {
        this.gA = gA;
        this.beginner = 0;
    }

    // it starts the game over (setting the board to look like in the beginning).
    public void setBeginningBoard() {
        int[] white = {R.mipmap.rook_white, R.mipmap.knight_white, R.mipmap.bishop_white,
                R.mipmap.king_white, R.mipmap.queen_white, R.mipmap.bishop_white,
                R.mipmap.knight_white, R.mipmap.rook_white, R.mipmap.pawn_white};
        int[] black = {R.mipmap.rook_black, R.mipmap.knight_black, R.mipmap.bishop_black,
                R.mipmap.king_black, R.mipmap.queen_black, R.mipmap.bishop_black,
                R.mipmap.knight_black, R.mipmap.rook_black, R.mipmap.pawn_black};
        String[] figs = {"rook", "knight", "bishop", "king", "queen", "bishop", "knight", "rook", "pawn"};
        for (int i = 0; i < gA.getBoard().length; i++) {
            gA.getBoard()[0][i].setImageResource(white[i]);
            gA.getBoard()[0][i].setRotation(180);
            figBoard[0][i] = new Figure(figs[i], "white", white[i]);
        }
        for (int i = 0; i < gA.getBoard().length; i++) {
            gA.getBoard()[1][i].setImageResource(white[8]);
            gA.getBoard()[1][i].setRotation(180);
            figBoard[1][i] = new Figure(figs[8], "white", white[8]);
        }
        for (int i = 0; i < gA.getBoard().length; i++) {
            gA.getBoard()[6][i].setImageResource(black[8]);
            gA.getBoard()[6][i].setRotation(0);
            figBoard[6][i] = new Figure(figs[8], "black", black[8]);
        }
        for (int i = 0; i < gA.getBoard().length; i++) {
            gA.getBoard()[7][i].setImageResource(black[i]);
            gA.getBoard()[7][i].setRotation(0);
            figBoard[7][i] = new Figure(figs[i], "black", black[i]);
        }
        for (int j = 2; j < 6; j++) {
            for (int i = 0; i < gA.getBoard().length; i++) {
                gA.getBoard()[j][i].setImageResource(0);
                figBoard[j][i] = new Figure("none", "none", 0);
            }
        }
        clearFallenFigures();
        clearTags();
        clearBoardBackground();
        numberOfBlackFallen = 0;
        numberOfWhiteFallen = 0;
        fallenFiguresWhite = new ArrayList<Figure>();
        fallenFiguresBlack = new ArrayList<Figure>();

        if (beginner % 2 == 0)
            this.turn = 0;
        else this.turn = 1;

        setBoardClickable();
        showScore();
        showTurn();
        showCheck();
        beginner++;

    }

    public void clearTags() {
        for (int i = 0; i < gA.getBoard().length; i++) {
            for (int j = 0; j < gA.getBoard().length; j++) {
                gA.getBoard()[i][j].setTag("");
            }
        }
    }

    public void clearBoardBackground() {
        for (int i = 0; i < gA.getBoard().length; i++) {
            for (int j = 0; j < gA.getBoard().length; j++) {
                if ((i + j) % 2 == 1) {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.white);
                } else {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.someBlue);
                }
            }
        }
    }

    public void clearButton(int position) {
        int x = position / 10;
        int y = position % 10;
        gA.getBoard()[x][y].setImageResource(0);
        gA.getBoard()[x][y].setRotation(0);
        figBoard[x][y].setColor("none");
        figBoard[x][y].setShape("none");
        figBoard[x][y].setImageResource(0);
    }

    public void clearFallenFigures() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                gA.getFallenFiguresWhite()[i][j].setImageResource(0);
                gA.getFallenFiguresBlack()[i][j].setImageResource(0);
            }
        }
    }

    //shows the options to move the piece.
    public void showOptions(ImageButton b) {
        clearBoardBackground();
        clearTags();
        int position = b.getId();
        int x = position / 10;
        int y = position % 10;
        String color = this.figBoard[x][y].getColor();
        if (turn % 2 == 0 && color.equals("white")) {
            switch (this.figBoard[x][y].getShape()) {
                case "rook":
                    Rook rook = new Rook(color, gA.getBoard(), this.figBoard, position);
                    rook.showOptions();
                    break;
                case "king":
                    King king = new King(color, gA.getBoard(), this.figBoard, position);
                    king.showOptions();
                    break;
                case "knight":
                    Knight knight = new Knight(color, gA.getBoard(), this.figBoard, position);
                    knight.showOptions();
                    break;
                case "bishop":
                    Bishop bishop = new Bishop(color, gA.getBoard(), this.figBoard, position);
                    bishop.showOptions();
                    break;
                case "queen":
                    Queen queen = new Queen(color, gA.getBoard(), this.figBoard, position);
                    queen.showOptions();
                    break;
                case "pawn":
                    Pawn pawn = new Pawn(color, gA.getBoard(), this.figBoard, position);
                    pawn.showOptions();
                    break;
            }
        } else if (turn % 2 == 1 && color.equals("black")) {
            switch (this.figBoard[x][y].getShape()) {
                case "rook":
                    Rook rook = new Rook(color, gA.getBoard(), this.figBoard, position);
                    rook.showOptions();
                    break;
                case "king":
                    King king = new King(color, gA.getBoard(), this.figBoard, position);
                    king.showOptions();
                    break;
                case "knight":
                    Knight knight = new Knight(color, gA.getBoard(), this.figBoard, position);
                    knight.showOptions();
                    break;
                case "bishop":
                    Bishop bishop = new Bishop(color, gA.getBoard(), this.figBoard, position);
                    bishop.showOptions();
                    break;
                case "queen":
                    Queen queen = new Queen(color, gA.getBoard(), this.figBoard, position);
                    queen.showOptions();
                    break;
                case "pawn":
                    Pawn pawn = new Pawn(color, gA.getBoard(), this.figBoard, position);
                    pawn.showOptions();
                    break;
            }
        }
    }

    //adds the figures that died to the place where we show them (and to a arrayList)
    public void AddFallenToScrollView(Figure figure) {
        if (figure.getColor().equals("white")) {
            gA.getFallenFiguresWhite()[numberOfWhiteFallen / 8][numberOfWhiteFallen % 8].setImageResource(figure.getImageResource());
            fallenFiguresWhite.add(figure);
            numberOfWhiteFallen++;
        } else {
            gA.getFallenFiguresBlack()[numberOfBlackFallen / 8][numberOfBlackFallen % 8].setImageResource(figure.getImageResource());
            fallenFiguresBlack.add(figure);
            numberOfBlackFallen++;
        }
        if (figure.getShape().equals("king")) {
            winner(figure);
        }
    }

    public void moveFromTo(int lastPosition, int movingTo) {
        int x = lastPosition / 10; // x and y of last position
        int y = lastPosition % 10;

        Figure theMovedFigure = new Figure("none", "none", 0);
        theMovedFigure.copy(figBoard[x][y]); // copy the moving figure
        clearButton(lastPosition); //clear the button in last position

        x = movingTo / 10; // x and y of the place the figure is gonna move to
        y = movingTo % 10;

        // if there is a figure on "movingTo" then save it and show it where we show the seas heroes
        if (gA.getBoard()[x][y].getTag().equals("possibleKill")) {
            Figure fallen = new Figure("none", "none", 0);
            fallen.copy(figBoard[x][y]);
            AddFallenToScrollView(fallen);
        }

        //move the figure to it's new place
        figBoard[x][y].setShape(theMovedFigure.getShape());
        figBoard[x][y].setColor(theMovedFigure.getColor());
        figBoard[x][y].setImageResource(theMovedFigure.getImageResource());
        gA.getBoard()[x][y].setImageResource(theMovedFigure.getImageResource());

        // if the moving figure is white then turn it upside down, if not then make it normal
        if (theMovedFigure.getColor().equals("white"))
            gA.getBoard()[x][y].setRotation(180);
        else gA.getBoard()[x][y].setRotation(0);

        if (theMovedFigure.getShape().equals("king")) {
            if (theMovedFigure.getColor().equals("white")) {
                this.kingWhite = movingTo;
            } else {
                this.kingBlack = movingTo;
            }
        }

        turn++;
        showTurn();
        showCheck();

        // clear the board and the tags for the next move
        clearTags();
        clearBoardBackground();
    }

    public void winner(Figure deadKing) {
        String message = "";
        String title = "";
        if (deadKing.getColor().equals("white")) {
            title = "Black won!!! :D";
            message = "White lost!!! :(";
            this.blackWon++;
        } else {
            title = "White won!!! :D";
            message = "Black lost!!! :(";
            this.whiteWon++;
        }
        this.gamesPlayed++;
        showScore();
        gA.winner(title, message);
        setBoardUnClickable();
    }

    public void reset() {
        this.whiteWon = 0;
        this.blackWon = 0;
        this.gamesPlayed = 0;
    }

    public void showScore() {
        gA.getBlackWon().setText("You won: " + this.blackWon + "/" + this.gamesPlayed);
        gA.getWhiteWon().setText("You won: " + this.whiteWon + "/" + this.gamesPlayed);
    }

    public void setBoardUnClickable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gA.getBoard()[i][j].setClickable(false);
            }
        }
    }

    public void setBoardClickable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gA.getBoard()[i][j].setClickable(true);
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    public void showTurn() {
        if (turn % 2 == 0) {
            gA.getBlackTurn().setText("Wait");
            gA.getBlackTurn().setTextColor(R.color.darkPurple);
            gA.getWhiteTurn().setText("Your turn");
            gA.getWhiteTurn().setTextColor(R.color.darkGreen);
        } else {
            gA.getBlackTurn().setText("Your turn");
            gA.getBlackTurn().setTextColor(R.color.darkGreen);
            gA.getWhiteTurn().setText("Wait");
            gA.getWhiteTurn().setTextColor(R.color.darkPurple);
        }
    }

    public void showCheck() {
        King whiteKing = new King("white", gA.getBoard(), figBoard, kingWhite);
        King blackKing = new King("black", gA.getBoard(), figBoard, kingBlack);

        if (whiteKing.isCheck()) {
            gA.getCheckWhite().setText("Check");
        } else {
            gA.getCheckWhite().setText("");
        }
        if (blackKing.isCheck()) {
            gA.getCheckBlack().setText("Check");
        } else {
            gA.getCheckBlack().setText("");
        }
    }

}
