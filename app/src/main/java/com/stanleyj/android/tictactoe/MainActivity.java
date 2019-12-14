package com.stanleyj.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button[][] buttons = new Button[3][3];
    private boolean player1turn = true;
    private int roundcount;
    private int player1points, player2points;
    private TextView tv1, tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "This game is for 2 Players", Toast.LENGTH_SHORT).show();
        tv1 = (TextView) findViewById(R.id.tv_p1);
        tv2 = (TextView) findViewById(R.id.tv_p2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonId = "but_" + i + j;
                int resID = getResources().getIdentifier(buttonId, "id", getPackageName());
                buttons[i][j] = (Button) findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }

        }
        Button buttonReset = (Button) findViewById(R.id.Reset_button);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetboard();
                player1points = 0;
                player2points = 0;
                updatePointsText();

            }
        });

    }

    @Override
    public void onClick(View v) {

        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (player1turn) {
            ((Button) v).setText("X");
//            Toast.makeText(this, "Player2 Turn", Toast.LENGTH_SHORT).show();

        } else {
            ((Button) v).setText("O");
//            Toast.makeText(this, "Player1 Turn", Toast.LENGTH_SHORT).show();


        }

        roundcount++;
        if (checkForWin()) {
            if (player1turn) {
                player1wins();
            } else {
                player2wins();
            }
        } else if (roundcount == 9) {
            draw();
        } else {
            player1turn = !player1turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();

            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void draw() {
        Toast.makeText(this, "Its a Draw!", Toast.LENGTH_SHORT).show();
        resetboard();
    }

    private void resetboard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundcount = 0;
        player1turn = true;
    }

    private void player2wins() {
        player2points++;
        Toast.makeText(this, "Player2 Has Won!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetboard();
    }

    private void player1wins() {
        player1points++;
        Toast.makeText(this, "Player1 Has Won!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetboard();
    }

    private void updatePointsText() {
        tv1.setText("Player 1: " + player1points);
        tv2.setText("Player 2: " + player2points);
    }


}

