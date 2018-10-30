package com.yulia.milich.chess;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TheChessGame extends AppCompatActivity implements View.OnClickListener{

    private ImageButton board[][] = new ImageButton[8][8];
    private TableRow rows[] = new TableRow[8];
//    private boolean oneClick = true;
//    private ImageButton helpButton;
    private ChessManager cM;
    private int lastPosition;
    private ImageView[][] fallenFiguresWhite = new ImageView[2][8];
    private ImageView[][] fallenFiguresBlack = new ImageView[2][8];
    private TableRow rowWhite[] = new TableRow[2];
    private TableRow rowBlack[] = new TableRow[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_chess_game);

        cM = new ChessManager(this);

        createBoard();
        cM.setBeginningBoard(); // placing the figures in the right place for the beginning of the game

        createScrollingListsForFallen();


    }

    public void onClick(View v) {
        switch (v.getId()){
//            case 44:
//                break;
            default:
//                if (oneClick){
//                    //oneClick =false;
////                    helpButton = (ImageButton) v;
//                    cM.showOptions((ImageButton) v);
//
//                }
                if(v.getTag().equals("possibleMove")){
                    cM.moveToEmptyPlaceFromAGivenPlace(this.lastPosition, v.getId());
                }
                else if(v.getTag().equals("possibleKill")){
                    cM.moveToKillFromAGivenPlace(this.lastPosition, v.getId());
                }
                else{
                    cM.showOptions((ImageButton) v);
                    this.lastPosition = v.getId();
                }
        }
//        switch (v.getTag())
//        {
//            case R.mipmap.rook_white:
//                Rook figure = new Rook("white");
//                break;
//            case "king_white":
//                King figure = new King("white");
//                break;
//
//        }
    }

    public ImageButton[][] getBoard(){
        return board;
    }

    public ImageView[][] getFallenFiguresBlack() {
        return fallenFiguresBlack;
    }

    public ImageView[][] getFallenFiguresWhite() {
        return fallenFiguresWhite;
    }

    public void createBoard(){
        TableLayout l1 = (TableLayout) findViewById(R.id.chessBoard);

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

        for (int i=0; i<8; i++){
            rows[i] = new TableRow(this); // creating the rows in the TableLayout
            for(int j=0; j<8; j++){
                board[i][j] = new ImageButton(this); // creating the button and setting an id
                board[i][j].setId(i*10+j);

                board[i][j].setTag(""); // setting a tag - the tag represents the figure that is placed on the button
                board[i][j].setLayoutParams(new TableRow.LayoutParams(width, height)); // setting the size of the button

                // setting the background so it will be a chess board (with black and white, except now it is blue and white)
//                if((i+j)%2 == 1){
//                    board[i][j].setBackgroundResource(R.color.white);
//                }
//                else {
//                    board[i][j].setBackgroundResource(R.color.someBlue);
//                }

                board[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP); // that way the image on the button is in the right size
                board[i][j].setOnClickListener(this);
                rows[i].addView(board[i][j]);
            }
            l1.addView(rows[i]);
        }
        cM.clearBoardBackground();
    }

    public void createScrollingListsForFallen(){
        TableLayout fallenBlack = (TableLayout) findViewById(R.id.fallenBlack);
        TableLayout fallenWhite = (TableLayout) findViewById(R.id.fallenWhite);

//        TableRow rowWhite[] = new TableRow[2];
//        TableRow rowBlack[] = new TableRow[2];

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

        for (int i = 0; i<2; i++){
            rowWhite[i] = new TableRow(this);
            rowBlack[i] = new TableRow(this);
            for (int j = 0; j<8; j++){
                this.fallenFiguresWhite[i][j] = new ImageView(this);
                this.fallenFiguresBlack[i][j] = new ImageView(this);

                this.fallenFiguresWhite[i][j].setId(100+i*10 + j);
                this.fallenFiguresBlack[i][j].setId(200+i*10 + j);

                this.fallenFiguresWhite[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);
                this.fallenFiguresBlack[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP);

                this.fallenFiguresWhite[i][j].setLayoutParams(new TableRow.LayoutParams(width, height));
                this.fallenFiguresBlack[i][j].setLayoutParams(new TableRow.LayoutParams(width, height));

                rowWhite[i].addView(this.fallenFiguresWhite[i][j]);
                rowBlack[i].addView(this.fallenFiguresBlack[i][j]);
            }
            fallenWhite.addView(rowWhite[i]);
            fallenBlack.addView(rowBlack[i]);
        }
    }
}
