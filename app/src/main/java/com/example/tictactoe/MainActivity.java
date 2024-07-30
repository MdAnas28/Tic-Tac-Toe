package com.example.tictactoe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView ScorePlayer1,ScorePlayer2, playerStatus;
    private Button playagain,reset;
    private Button[] btn = new Button[9] ;
    boolean playOneActive;
    private int round;
    private int playerOneScoreCount,playerTwoScoreCount;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPosition = {
            {0, 1, 2}, // Row 1
            {3, 4, 5}, // Row 2
            {6, 7, 8}, // Row 3
            {0, 3, 6}, // Column 1
            {1, 4, 7}, // Column 2
            {2, 5, 8}, // Column 3
            {0, 4, 8}, // Diagonal 1
            {2, 4, 6}  // Diagonal 2
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScorePlayer1 = findViewById(R.id.PlayerScore1);
        ScorePlayer2 = findViewById(R.id.PlayerScore2);
        playagain = findViewById(R.id.playAgain_btn);
        reset = findViewById(R.id.Reset_btn);
        playerStatus = findViewById(R.id.playerStatus);



        btn[0] = findViewById(R.id.btn_0);
        btn[1] = findViewById(R.id.btn_1);
        btn[2] = findViewById(R.id.btn_2);
        btn[3] = findViewById(R.id.btn_3);
        btn[4] = findViewById(R.id.btn_4);
        btn[5] = findViewById(R.id.btn_5);
        btn[6] = findViewById(R.id.btn_6);
        btn[7] = findViewById(R.id.btn_7);
        btn[8] = findViewById(R.id.btn_8);

        for (int i=0; i<btn.length; i++){

            btn[i].setOnClickListener(this);
        }


        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        round = 0;
        playOneActive = true;

    }


    @Override
    public void onClick(View view) {

        if(!((Button)view).getText().toString().equals(""))
        {
                return;
        }
        else if(checkWinner())
        {
            return;
        }

        String btnid = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(btnid.substring(btnid.length()-1,btnid.length()));

        if(playOneActive)
        {
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#70fc3a"));
            gameState[gameStatePointer] = 0;

        }
        else
        {
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 1;

        }

        round++;

        if(checkWinner())
        {
            if(playOneActive)
            {
            playerOneScoreCount++;
            updatePlayerScore();
            playerStatus.setText("player 1 win ");
            }
            else
            {
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText("player 2 win");
            }
        }
        else if(round==9)
        {
            playerStatus.setText("Draw");
        }
        else
        {
            playOneActive = !playOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });

        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playAgain();
            }
        });


    }

    private boolean checkWinner(){

        boolean winnerResult = false;
        for(int[] winningPosition: winningPosition)
        {
            if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                   gameState[winningPosition[1]]==gameState[winningPosition[2]] &&
                    gameState[winningPosition[0]]!= 2)
            {
                winnerResult = true;
            }
        }
        return winnerResult;
    }

    private void playAgain()
    {
        round = 0;
        playOneActive = true;
        for(int i =0; i<btn.length;i++)
        {
            gameState[i] = 2;
            btn[i].setText("");
        }
        playerStatus.setText("status");
    }

    private void updatePlayerScore(){

        ScorePlayer1.setText(Integer.toString(playerOneScoreCount));
        ScorePlayer2.setText(Integer.toString(playerTwoScoreCount));

    }
}