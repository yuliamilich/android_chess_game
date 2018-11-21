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
    private Figure[][] figBoard = new Figure[8][8];
    private Figure theMovedFigure = new Figure("none", "none", 0);
    private ArrayList<Figure> fallenFiguresWhite;
    private ArrayList<Figure> fallenFiguresBlack;
    private int numberOfWhiteFallen;
    private int numberOfBlackFallen;
//    private static String[] tagsWhite = {"rook_white", "knight_white", "bishop_white", "king_white", "queen_white",
//            "bishop_white", "knight_white", "rook_white", "pawn_white"};
//    private static String[] tagsBlack = {"rook_black", "knight_black", "bishop_black", "king_black", "queen_black",
//            "bishop_black", "knight_black", "rook_black", "pawn_black"};

    public ChessManager (TheChessGame gA){
        this.gA = gA;
    }

// it starts the game over (setting the board to look like in the beginning.
    public void setBeginningBoard(){
        for (int i=0; i<gA.getBoard().length; i++){
            gA.getBoard()[0][i].setImageResource(white[i]);
            gA.getBoard()[0][i].setRotation(180);
            figBoard[0][i] = new Figure(figs[i], "white", white[i]);
        }
        for (int i=0; i<gA.getBoard().length; i++){
            gA.getBoard()[1][i].setImageResource(white[8]);
            gA.getBoard()[1][i].setRotation(180);
            figBoard[1][i] = new Figure(figs[8], "white", white[8]);
        }
        for (int i=0; i<gA.getBoard().length; i++){
            gA.getBoard()[6][i].setImageResource(black[8]);
            figBoard[6][i] = new Figure(figs[8], "black", black[8]);
        }
        for (int i=0; i<gA.getBoard().length; i++){
            gA.getBoard()[7][i].setImageResource(black[i]);
            figBoard[7][i] = new Figure(figs[i], "black", black[i]);
        }
        for(int j = 2; j<6; j++){
            for(int i = 0; i<gA.getBoard().length; i++){
                figBoard[j][i] = new Figure("none", "none", 0);
            }
        }
        clearTags();
        numberOfBlackFallen = 0;
        numberOfWhiteFallen = 0;
        fallenFiguresWhite = new ArrayList<Figure>();
        fallenFiguresBlack = new ArrayList<Figure>();
    }

    public void clearTags(){
        for(int i=0; i<gA.getBoard().length; i++){
            for (int j = 0; j<gA.getBoard().length; j++){
                gA.getBoard()[i][j].setTag("");
            }
        }
    }

    public void clearBoardBackground(){
        for(int i=0; i<gA.getBoard().length; i++){
            for (int j = 0; j<gA.getBoard().length; j++){
                if((i+j)%2 == 1){
                    gA.getBoard()[i][j].setBackgroundResource(R.color.white);
                }
                else {
                    gA.getBoard()[i][j].setBackgroundResource(R.color.someBlue);
                }
            }
        }
    }

    public void clearButton(int position){
        int x= position/10;
        int y = position%10;
        gA.getBoard()[x][y].setImageResource(0);
        gA.getBoard()[x][y].setRotation(0);
        figBoard[x][y].setColor("none");
        figBoard[x][y].setShape("none");
        figBoard[x][y].setImageResource(0);
    }

    //shows the options to move the piece.
    public void showOptions(ImageButton b){
        clearBoardBackground();
        clearTags();
        int position = b.getId();
        int x = position / 10;
        int y = position % 10;
        String color = this.figBoard[x][y].getColor();
        switch ( this.figBoard[x][y].getShape())
        {
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

        }
    }

    public void moveToEmptyPlaceFromAGivenPlace(int lastPosition, int movingTo){
        int x = lastPosition / 10;
        int y = lastPosition % 10;
        //i don't know whether to make it a local variable or not
        //Figure theMovedFigure = figBoard[x][y];
        theMovedFigure.copy(figBoard[x][y]);
        clearButton(lastPosition);
        x = movingTo/10;
        y = movingTo%10;
        figBoard[x][y].setShape(theMovedFigure.getShape());
        figBoard[x][y].setColor(theMovedFigure.getColor());
        figBoard[x][y].setImageResource(theMovedFigure.getImageResource());
        gA.getBoard()[x][y].setImageResource(theMovedFigure.getImageResource());
        if(theMovedFigure.getColor().equals("white"))
            gA.getBoard()[x][y].setRotation(180);
        else gA.getBoard()[x][y].setRotation(0);
        clearTags();
        clearBoardBackground();

    }

    public void moveToKillFromAGivenPlace(int lastPosition, int movingTo){
        int x = lastPosition / 10;
        int y = lastPosition % 10;
        //fallen.copy(figBoard[x][y]);
        //i don't know whether to make it a local variable or not
        //Figure theMovedFigure = figBoard[x][y];
        theMovedFigure.copy(figBoard[x][y]);
        clearButton(lastPosition);
        x = movingTo/10;
        y = movingTo%10;
        Figure fallen = new Figure("none", "none", 0);
        fallen.copy(figBoard[x][y]);
        figBoard[x][y].setShape(theMovedFigure.getShape());
        figBoard[x][y].setColor(theMovedFigure.getColor());
        figBoard[x][y].setImageResource(theMovedFigure.getImageResource());
        gA.getBoard()[x][y].setImageResource(theMovedFigure.getImageResource());
        if(theMovedFigure.getColor().equals("white"))
            gA.getBoard()[x][y].setRotation(180);
        else gA.getBoard()[x][y].setRotation(0);
        clearTags();
        clearBoardBackground();

        //and the change from the moveToEmptyPlaceFromAGivenPlace
        //is that we need to save the fallen figure in a list and put an image beneath (or above) the board.
        AddFallenPictureToScrollView(fallen);
        if(fallen.getColor().equals("white")){
            fallenFiguresWhite.add(fallen);
            numberOfWhiteFallen++;
        }
        else {
            fallenFiguresBlack.add(fallen);
            numberOfBlackFallen++;
        }


    }

    public void AddFallenPictureToScrollView(Figure figure){
        if(figure.getColor().equals("white")){
            gA.getFallenFiguresWhite()[numberOfWhiteFallen/8][numberOfWhiteFallen%8].setImageResource(figure.getImageResource());
        }
        else{
            gA.getFallenFiguresBlack()[numberOfBlackFallen/8][numberOfBlackFallen%8].setImageResource(figure.getImageResource());
        }
    }

}
