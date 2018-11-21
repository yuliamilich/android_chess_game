package com.yulia.milich.chess;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;


public class ChessManager {
    private int[] white = {R.mipmap.rook_white, R.mipmap.knight_white, R.mipmap.bishop_white,
            R.mipmap.king_white, R.mipmap.queen_white, R.mipmap.bishop_white,
            R.mipmap.knight_white, R.mipmap.rook_white, R.mipmap.pawn_white};
    private int[] black = {R.mipmap.rook_black, R.mipmap.knight_black, R.mipmap.bishop_black,
            R.mipmap.king_black, R.mipmap.queen_black, R.mipmap.bishop_black,
            R.mipmap.knight_black, R.mipmap.rook_black, R.mipmap.pawn_black};
    private String[] figs = {"rook", "knight", "bishop", "king", "queen", "bishop", "knight", "rook", "pawn"};
    private TheChessGame gA;
    private String[][] strBoard = new String[8][8];
    private boolean[][] booleanBoard = new boolean[8][8];
    private Figure[][] figBoard = new Figure[8][8];

    //private Figure theMovedFigure = new Figure("none", "none", 0);
    private ArrayList<Figure> fallenFiguresWhite;
    private ArrayList<Figure> fallenFiguresBlack;
    private int numberOfWhiteFallen;
    private int numberOfBlackFallen;

    private int turn;
    private int beginner = 0;

    private int lastPosition;

    public ChessManager(TheChessGame gA) {
        this.gA = gA;
    }

    // it starts the game over (setting the board to look like in the beginning.
    public void setBeginningBoard() {
        clearBooleanBoard();

        figBoard[0][0] = new Rook("white", 00, white[0]);
        figBoard[0][1] = new Knight("white", 01, white[1]);
        figBoard[0][2] = new Bishop("white", 02, white[2]);
        figBoard[0][3] = new King("white", 03, white[3]);
        figBoard[0][4] = new Queen("white", 04, white[4]);
        figBoard[0][5] = new Bishop("white", 05, white[2]);
        figBoard[0][6] = new Knight("white", 06, white[1]);
        figBoard[0][7] = new Rook("white", 07, white[0]);

        for (int i = 0; i < gA.getBoard().length; i++) {
            booleanBoard[0][i] = true;
            gA.getBoard()[0][i].setRotation(180);
        }

        for (int i = 0; i < gA.getBoard().length; i++) {
            booleanBoard[1][i] = true;
            gA.getBoard()[1][i].setRotation(180);
            figBoard[1][i] = new Pawn("white", 10 + i, white[8]);
        }

        figBoard[7][0] = new Rook("black", 70, white[0]);
        figBoard[7][1] = new Knight("black", 71, white[1]);
        figBoard[7][2] = new Bishop("black", 72, white[2]);
        figBoard[7][3] = new King("black", 73, white[3]);
        figBoard[7][4] = new Queen("black", 74, white[4]);
        figBoard[7][5] = new Bishop("black", 75, white[2]);
        figBoard[7][6] = new Knight("black", 76, white[1]);
        figBoard[7][7] = new Rook("black", 77, white[0]);

        for (int i = 0; i < gA.getBoard().length; i++) {
            booleanBoard[7][i] = true;
            gA.getBoard()[7][i].setRotation(0);
        }

        for (int i = 0; i < gA.getBoard().length; i++) {
            booleanBoard[6][i] = true;
            gA.getBoard()[6][i].setRotation(0);
            figBoard[6][i] = new Pawn("black", 60 + i, black[8]);
        }

        clearBoardBackground();
        clearStringBoard();
        showBoard();

        if (beginner % 2 == 0)
            this.turn = 0;
        else this.turn = 1;

        setBoardClickable();
//        showScore();
//        showTurn();
        beginner++;

        numberOfBlackFallen = 0;
        numberOfWhiteFallen = 0;
        fallenFiguresWhite = new ArrayList<Figure>();
        fallenFiguresBlack = new ArrayList<Figure>();
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

    public void clearBooleanBoard() {
        for (int i = 0; i < booleanBoard.length; i++) {
            for (int j = 0; j < booleanBoard.length; j++) {
                booleanBoard[i][j] = false;
            }
        }
    }

    public void clearStringBoard() {
        for (int i = 0; i < strBoard.length; i++) {
            for (int j = 0; j < strBoard.length; j++) {
                strBoard[i][j] = "";
            }
        }
    }

    public void setBoardClickable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //if (booleanBoard[i][j])
                gA.getBoard()[i][j].setClickable(true);
            }
        }
    }

    public void setBoardUnClickable() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (booleanBoard[i][j])
                    gA.getBoard()[i][j].setClickable(false);
            }
        }
    }

    public void showBoard() {
        for (int i = 0; i < booleanBoard.length; i++) {
            for (int j = 0; j < booleanBoard.length; j++) {
                if (strBoard[i][j].equals("possible move")) {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.green);
                }
                if (strBoard[i][j].equals("possible kill")) {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.red);
                }
                if (booleanBoard[i][j]) {
                    if (figBoard[i][j].getColor().equals("white")) {
                        gA.getBoard()[i][j].setRotation(180);
                    } else {
                        gA.getBoard()[i][j].setRotation(0);
                    }
                    gA.getBoard()[i][j].setImageResource(figBoard[i][j].getImageResource());
                } else {
                    gA.getBoard()[i][j].setImageResource(0);
                }
            }
        }
    }

//    public void clearButton(int position){
//        int x= position/10;
//        int y = position%10;
//        gA.getBoard()[x][y].setImageResource(0);
//        gA.getBoard()[x][y].setRotation(0);
//        figBoard[x][y].setColor("none");
//        figBoard[x][y].setShape("none");
//        figBoard[x][y].setImageResource(0);
//    }

    //shows the options to move the piece.
//    public void showOptions(ImageButton b) {
//        clearBoardBackground();
//        clearTags();
//        int position = b.getId();
//        int x = position / 10;
//        int y = position % 10;
//        String color = this.figBoard[x][y].getColor();
//        switch (this.figBoard[x][y].getShape()) {
//            case "rook":
//                Rook rook = new Rook(color, gA.getBoard(), this.figBoard, position);
//                rook.showOptions();
//                break;
//            case "king":
//                King king = new King(color, gA.getBoard(), this.figBoard, position);
//                king.showOptions();
//                break;
//            case "knight":
//                Knight knight = new Knight(color, gA.getBoard(), this.figBoard, position);
//                knight.showOptions();
//                break;
//
//        }
//    }

    public void click(int newPosition) {
        int x = newPosition / 10;
        int y = newPosition % 10;

        if (strBoard[x][y].equals("possible move") || strBoard[x][y].equals("possible kill")) {
            if(strBoard[x][y].equals("possible kill")){
                AddFallenPictureToScrollView(figBoard[x][y]);
            }
            moveFromTo(this.lastPosition, newPosition);
        } else {
            clearBoardBackground();
            clearStringBoard();
            if (booleanBoard[x][y]) {
                figBoard[x][y].move(figBoard, booleanBoard, strBoard);
                this.lastPosition = newPosition;
                showBoard();
            }
        }
    }

    public void moveFromTo(int lastPosition, int newPosition) {
        int x = lastPosition / 10;
        int y = lastPosition % 10;

        int nx = newPosition / 10;
        int ny = newPosition % 10;

        figBoard[nx][ny] = figBoard[x][y];
        figBoard[nx][ny].setPosition(newPosition);

        booleanBoard[nx][ny] = true;
        booleanBoard[x][y] = false;

        clearStringBoard();
        clearBoardBackground();
        showBoard();
    }

//    public void moveToEmptyPlaceFromAGivenPlace(int lastPosition, int movingTo) {
//        int x = lastPosition / 10;
//        int y = lastPosition % 10;
//        //i don't know whether to make it a local variable or not
//        //Figure theMovedFigure = figBoard[x][y];
//        theMovedFigure.copy(figBoard[x][y]);
//        clearButton(lastPosition);
//        x = movingTo / 10;
//        y = movingTo % 10;
//        figBoard[x][y].setShape(theMovedFigure.getShape());
//        figBoard[x][y].setColor(theMovedFigure.getColor());
//        figBoard[x][y].setImageResource(theMovedFigure.getImageResource());
//        gA.getBoard()[x][y].setImageResource(theMovedFigure.getImageResource());
//        if (theMovedFigure.getColor().equals("white"))
//            gA.getBoard()[x][y].setRotation(180);
//        else gA.getBoard()[x][y].setRotation(0);
//        clearTags();
//        clearBoardBackground();
//
//    }
//
//    public void moveToKillFromAGivenPlace(int lastPosition, int movingTo) {
//        int x = lastPosition / 10;
//        int y = lastPosition % 10;
//        //fallen.copy(figBoard[x][y]);
//        //i don't know whether to make it a local variable or not
//        //Figure theMovedFigure = figBoard[x][y];
//        theMovedFigure.copy(figBoard[x][y]);
//        clearButton(lastPosition);
//        x = movingTo / 10;
//        y = movingTo % 10;
//        Figure fallen = new Figure("none", "none", 0);
//        fallen.copy(figBoard[x][y]);
//        figBoard[x][y].setShape(theMovedFigure.getShape());
//        figBoard[x][y].setColor(theMovedFigure.getColor());
//        figBoard[x][y].setImageResource(theMovedFigure.getImageResource());
//        gA.getBoard()[x][y].setImageResource(theMovedFigure.getImageResource());
//        if (theMovedFigure.getColor().equals("white"))
//            gA.getBoard()[x][y].setRotation(180);
//        else gA.getBoard()[x][y].setRotation(0);
//        clearTags();
//        clearBoardBackground();
//
//        //and the change from the moveToEmptyPlaceFromAGivenPlace
//        //is that we need to save the fallen figure in a list and put an image beneath (or above) the board.
//        AddFallenPictureToScrollView(fallen);
//        if (fallen.getColor().equals("white")) {
//            fallenFiguresWhite.add(fallen);
//            numberOfWhiteFallen++;
//        } else {
//            fallenFiguresBlack.add(fallen);
//            numberOfBlackFallen++;
//        }
//
//
//    }

    public void AddFallenPictureToScrollView(Figure figure) {
        if (figure.getColor().equals("white")) {
            gA.getFallenFiguresWhite()[numberOfWhiteFallen / 8][numberOfWhiteFallen % 8].setImageResource(figure.getImageResource());
        } else {
            gA.getFallenFiguresBlack()[numberOfBlackFallen / 8][numberOfBlackFallen % 8].setImageResource(figure.getImageResource());
        }
    }

}
