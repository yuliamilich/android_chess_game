package com.yulia.milich.chess;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private String[][] strBoard = new String[8][8];  //represents where a figure can move
    private boolean[][] booleanBoard = new boolean[8][8];  //the board represents weather there is a figure on on the spot or not
    private Figure[][] figBoard = new Figure[8][8];
    SQLiteDatabase sqdb;
    DBUsers users;

    private ArrayList<Figure> fallenFiguresWhite;  // a list of all eaten figures
    private ArrayList<Figure> fallenFiguresBlack;
    private int numberOfWhiteFallen;
    private int numberOfBlackFallen;

    private int whiteWon = 0;
    private int blackWon = 0;
    private int gamesPlayed = 0;

    private int turn;
    private int beginner = 1;

    private int lastPosition;

    private King whiteKing = new King("white", 04, white[3]);
    private King blackKing = new King("black", 74, black[3]);

    public ChessManager(TheChessGame gA) {
        this.gA = gA;
    }

    // it starts the game over (setting the board to look like in the beginning.
    public void setBeginningBoard() {
        clearBooleanBoard();

        figBoard[0][0] = new Rook("white", 00, white[0]);
        figBoard[0][1] = new Knight("white", 01, white[1]);
        figBoard[0][2] = new Bishop("white", 02, white[2]);
        figBoard[0][4] = new King("white", 04, white[3]);
        figBoard[0][3] = new Queen("white", 03, white[4]);
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

        figBoard[7][0] = new Rook("black", 70, black[0]);
        figBoard[7][1] = new Knight("black", 71, black[1]);
        figBoard[7][2] = new Bishop("black", 72, black[2]);
        figBoard[7][4] = new King("black", 74, black[3]);
        figBoard[7][3] = new Queen("black", 73, black[4]);
        figBoard[7][5] = new Bishop("black", 75, black[2]);
        figBoard[7][6] = new Knight("black", 76, black[1]);
        figBoard[7][7] = new Rook("black", 77, black[0]);

        for (int i = 0; i < gA.getBoard().length; i++) {
            booleanBoard[7][i] = true;
            gA.getBoard()[7][i].setRotation(0);
        }

        for (int i = 0; i < gA.getBoard().length; i++) {
            booleanBoard[6][i] = true;
            gA.getBoard()[6][i].setRotation(0);
            figBoard[6][i] = new Pawn("black", 60 + i, black[8]);
        }

        clearFallenFigures();
        clearBoardBackground();
        clearStringBoard();
        showBoard();  //shows the figures
        showScore();
        setBoardClickable();

        if (beginner % 2 == 0)
            this.turn = 0;
        else this.turn = 1;

        beginner++;

        numberOfBlackFallen = 0;
        numberOfWhiteFallen = 0;
        fallenFiguresWhite = new ArrayList<Figure>();
        fallenFiguresBlack = new ArrayList<Figure>();

        showCheck();
        showTurn();
    }

    public void clearBoardBackground() {
        for (int i = 0; i < gA.getBoard().length; i++) {
            for (int j = 0; j < gA.getBoard().length; j++) {
                if ((i + j) % 2 == 1) {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.someBlue);
                } else {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.white);
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

    public void clearFallenFigures() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 8; j++) {
                gA.getFallenFiguresWhite()[i][j].setImageResource(0);
                gA.getFallenFiguresBlack()[i][j].setImageResource(0);
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

    //shows the figures in the right place and sets the background to be in the right color
    public void showBoard() {
        for (int i = 0; i < booleanBoard.length; i++) {
            for (int j = 0; j < booleanBoard.length; j++) {
                if (strBoard[i][j].equals("possible move") || strBoard[i][j].equals("castling")) {
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

    public void click(int newPosition) {
        int x = newPosition / 10;
        int y = newPosition % 10;
        showCheck();

        if (strBoard[x][y].equals("possible move") || strBoard[x][y].equals("possible kill") || strBoard[x][y].equals("castling")) {
            if (strBoard[x][y].equals("possible kill")) {
                AddFallenPictureToScrollView(figBoard[x][y]);
            }
            if(strBoard[x][y].equals("castling")){
                castling(this.lastPosition, newPosition);
            }
            moveFromTo(this.lastPosition, newPosition);
            turn++;
            showTurn();
            showCheck();
            if (figBoard[x][y].getShape().equals("king")) {
                if (figBoard[x][y].getColor().equals("white"))
                    whiteKing.setPosition(newPosition);
                else blackKing.setPosition(newPosition);
            }
        } else {
            clearBoardBackground();
            clearStringBoard();
            if (booleanBoard[x][y]) {
                if ((figBoard[x][y].getColor().equals("white") && turn % 2 == 0) || (figBoard[x][y].getColor().equals("black") && turn % 2 == 1)) {
                    figBoard[x][y].move(figBoard, booleanBoard, strBoard);
                    this.lastPosition = newPosition;
                    showBoard();
                }
            }
        }
        showCheck();
    }

    public void moveFromTo(int lastPosition, int newPosition) {
        int x = lastPosition / 10;
        int y = lastPosition % 10;

        int nx = newPosition / 10;
        int ny = newPosition % 10;

        figBoard[nx][ny] = figBoard[x][y];  //moving the figure
        figBoard[nx][ny].setPosition(newPosition);
        figBoard[nx][ny].setMoved();

        booleanBoard[nx][ny] = true;  //means that there is a figure on this spot
        booleanBoard[x][y] = false;

        if(figBoard[nx][ny].getShape().equals("pawn")){
            promotion(newPosition);
        }

        clearStringBoard();
        clearBoardBackground();
        showBoard();
    }

    public void castling(int lastPosition, int newPosition){
        int x = lastPosition / 10;
        int y = lastPosition % 10;

        int nx = newPosition / 10;
        int ny = newPosition % 10;

        if(y-ny == 2){
            moveFromTo(lastPosition, newPosition);
            moveFromTo(x*10+y-3, x*10+y-1);
        }
        else{
            moveFromTo(lastPosition, newPosition);
            moveFromTo(x*10+y+4, x*10+y+1);
        }
    }

    public void AddFallenPictureToScrollView(Figure figure) {
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

    public void winner(Figure deadKing) {
        String message = "";
        String title = "";
        if (deadKing.getColor().equals("white")) {
            title = gA.getBlackName() + " won!!! :D";
            message = gA.getWhiteName() + " lost!!! :(";
            this.blackWon++;
            updateGames("won", 1, gA.getBlackName());
        } else {
            title = gA.getWhiteName() + " won!!! :D";
            message = gA.getBlackName() + " lost!!! :(";
            this.whiteWon++;
            updateGames("won", 1, gA.getWhiteName());
        }
        this.gamesPlayed++;
        updateGames("played", 1, gA.getWhiteName());
        updateGames("played", 1, gA.getBlackName());
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
        if (whiteKing.isCheck(figBoard, booleanBoard, strBoard)) {
            gA.getCheckWhite().setText("Check");
        } else {
            gA.getCheckWhite().setText("");
        }
        if (blackKing.isCheck(figBoard, booleanBoard, strBoard)) {
            gA.getCheckBlack().setText("Check");
        } else {
            gA.getCheckBlack().setText("");
        }
    }

    //updates the data base
    public void updateGames(String what, int add, String name) {
        Cursor data = users.getItem(name);

        int itemID;
        int gamesPlayed;
        int gamesWon;
        while (data.moveToNext()) {
            int idColIndexx = data.getColumnIndex(users.UID);
            int gamesPlayedColIndexx = data.getColumnIndex(users.GAMESPLAYED);
            int gamesWonColIndexx = data.getColumnIndex(users.GAMESWON);

            itemID = data.getInt(idColIndexx);
            gamesPlayed = data.getInt(gamesPlayedColIndexx);
            gamesWon = data.getInt(gamesWonColIndexx);

            if (what.equals("won")) {
                users.updateGamesWon(String.valueOf(add + gamesWon), itemID);
            }
            else users.updateGamesPlayed(String.valueOf(add + gamesPlayed), itemID);

        }
    }

    public void promotion(int position){
        String color = figBoard[position/10][position%10].getColor();
        if(color.equals("black")){
            if(position/10 == 0){
                gA.promotion("black", position);

            }
        }
        else if(position/10 == 7){
            gA.promotion("white", position);

        }
    }

    public void promotionPartTwo(String color, int position) {
        if (color.equals("white")) {
            switch (gA.getPromotionFigure()) {
                case "queen":
                    figBoard[position / 10][position % 10] = new Queen("white", position, R.mipmap.queen_white);
                    break;
                case "rook":
                    figBoard[position / 10][position % 10] = new Rook("white", position, R.mipmap.rook_white);
                    break;
                case "bishop":
                    figBoard[position / 10][position % 10] = new Bishop("white", position, R.mipmap.bishop_white);
                    break;
                case "knight":
                    figBoard[position / 10][position % 10] = new Knight("white", position, R.mipmap.knight_white);
                    break;
            }
        }
        if (color.equals("black")) {
            switch (gA.getPromotionFigure()) {
                case "queen":
                    figBoard[position / 10][position % 10] = new Queen("black", position, R.mipmap.queen_black);
                    break;
                case "rook":
                    figBoard[position / 10][position % 10] = new Rook("black", position, R.mipmap.rook_black);
                    break;
                case "bishop":
                    figBoard[position / 10][position % 10] = new Bishop("black", position, R.mipmap.bishop_black);
                    break;
                case "knight":
                    figBoard[position / 10][position % 10] = new Knight("black", position, R.mipmap.knight_black);
                    break;
            }
        }
        showBoard();
    }
}
