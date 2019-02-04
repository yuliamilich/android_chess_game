package com.yulia.milich.chess;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
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
import android.widget.TextView;

public class TheChessGame extends AppCompatActivity implements View.OnClickListener {

    private ImageButton board[][] = new ImageButton[8][8];
    private ChessManager cM;
    private ImageView[][] fallenFiguresWhite = new ImageView[2][8];
    private ImageView[][] fallenFiguresBlack = new ImageView[2][8];
    private TextView whiteWon, blackWon;
    private TextView whiteTurn, blackTurn;
    private TextView checkBlack, checkWhite;
    private String whiteName, blackName;
    private String promotionFigure = "queen";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_chess_game);

        whiteName = (String) getIntent().getStringExtra("playerWhiteStr");
        blackName = (String) getIntent().getStringExtra("playerBlackStr");

        whiteWon = (TextView) findViewById(R.id.whiteWon);
        blackWon = (TextView) findViewById(R.id.blackWon);

        blackTurn = (TextView) findViewById(R.id.waitOrMoveBlack);
        whiteTurn = (TextView) findViewById(R.id.waitOrMoveWhite);

        checkBlack = (TextView) findViewById(R.id.checkBlack);
        checkWhite = (TextView) findViewById(R.id.checkWhite);

        cM = new ChessManager(this);

        cM.users = new DBUsers(this);
        cM.sqdb = cM.users.getWritableDatabase();

        createScrollingListsForFallen();

        createBoard();
        cM.setBeginningBoard(); // placing the figures in the right place for the beginning of the game

        ImageButton restart = (ImageButton) findViewById(R.id.restart);
        restart.setOnClickListener(this);

        ImageButton restart2 = (ImageButton) findViewById(R.id.restart2);
        restart2.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.restart:
                cM.setBeginningBoard();
                break;
            case R.id.restart2:
                cM.setBeginningBoard();
                break;
            case 1000:
                promotionFigure = "bishop";
                //promotionFigureChosen = true;
                break;
            case 2000:
                promotionFigure = "knight";
                break;
            case 3000:
                promotionFigure = "queen";
                break;
            case 4000:
                promotionFigure = "rook";
                break;

            default:
                cM.click(v.getId());
        }
    }

    public ImageButton[][] getBoard() {
        return board;
    }

    public ImageView[][] getFallenFiguresBlack() {
        return fallenFiguresBlack;
    }

    public ImageView[][] getFallenFiguresWhite() {
        return fallenFiguresWhite;
    }

    public TextView getBlackWon() {
        return blackWon;
    }

    public TextView getWhiteWon() {
        return whiteWon;
    }

    public TextView getCheckBlack() {
        return checkBlack;
    }

    public TextView getCheckWhite() {
        return checkWhite;
    }

    public TextView getBlackTurn() {
        return blackTurn;
    }

    public TextView getWhiteTurn() {
        return whiteTurn;
    }

    public String getBlackName() {
        return blackName;
    }

    public String getWhiteName() {
        return whiteName;
    }

    public String getPromotionFigure() {
        return promotionFigure;
    }

    public void createBoard() {
        TableLayout l1 = (TableLayout) findViewById(R.id.chessBoard);
        TableRow rows[] = new TableRow[8];

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

        for (int i = 0; i < 8; i++) {
            rows[i] = new TableRow(this); // creating the rows in the TableLayout
            for (int j = 0; j < 8; j++) {
                board[i][j] = new ImageButton(this); // creating the button and setting an id
                board[i][j].setId(i * 10 + j);

                board[i][j].setTag(""); // setting a tag - the tag represents the figure that is placed on the button
                board[i][j].setLayoutParams(new TableRow.LayoutParams(width, height)); // setting the size of the button

                board[i][j].setScaleType(ImageView.ScaleType.CENTER_CROP); // that way the image on the button is in the right size
                board[i][j].setOnClickListener(this);
                rows[i].addView(board[i][j]);
            }
            l1.addView(rows[i]);
        }
        cM.clearBoardBackground();
        cM.setBeginningBoard();
        cM.reset();
    }

    public void createScrollingListsForFallen() {
        TableLayout fallenBlack = (TableLayout) findViewById(R.id.fallenBlack);
        TableLayout fallenWhite = (TableLayout) findViewById(R.id.fallenWhite);

        TableRow rowWhite[] = new TableRow[2];
        TableRow rowBlack[] = new TableRow[2];

        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35, getResources().getDisplayMetrics());

        for (int i = 0; i < 2; i++) {
            rowWhite[i] = new TableRow(this);
            rowBlack[i] = new TableRow(this);
            for (int j = 0; j < 8; j++) {
                this.fallenFiguresWhite[i][j] = new ImageView(this);
                this.fallenFiguresBlack[i][j] = new ImageView(this);

                //i don't think this is useful
                this.fallenFiguresWhite[i][j].setId(100 + i * 10 + j);
                this.fallenFiguresBlack[i][j].setId(200 + i * 10 + j);

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

    public void winner(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TheChessGame.this);

        builder.setCancelable(true);
        builder.setTitle(title);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setMessage(message);
        builder.show();
    }

    public void promotion(final String color, final int position) {
        ImageView[] images = new ImageView[4];

        AlertDialog.Builder builder = new AlertDialog.Builder(TheChessGame.this);

        builder.setCancelable(true);
        builder.setTitle("pick a figure");

        for (int i = 0; i < images.length; i++) {
            images[i] = new ImageView(this);
        }

        if (color.equals("black")) {
            images[0].setImageResource(R.mipmap.bishop_black);
            images[1].setImageResource(R.mipmap.knight_black);
            images[2].setImageResource(R.mipmap.queen_black);
            images[3].setImageResource(R.mipmap.rook_black);
        } else {
            images[0].setImageResource(R.mipmap.bishop_white);
            images[1].setImageResource(R.mipmap.knight_white);
            images[2].setImageResource(R.mipmap.queen_white);
            images[3].setImageResource(R.mipmap.rook_white);
        }

        TableLayout tl = new TableLayout(this);
        TableRow tr = new TableRow(this);
        for (int i = 0; i < images.length; i++) {
            images[i].setId((i + 1) * 1000);
            images[i].setOnClickListener(this);
            tr.addView(images[i]);
        }
        tl.addView(tr);

        builder.setMessage("choose a figure");
        builder.setView(tl);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cM.promotionPartTwo(color, position);
                dialog.cancel();
            }
        });

        builder.show();

    }
}
