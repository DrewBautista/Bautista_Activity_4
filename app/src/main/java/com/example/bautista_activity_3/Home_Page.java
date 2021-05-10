package com.example.bautista_activity_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home_Page extends AppCompatActivity implements View.OnClickListener {

    TextView text_playerone_score, text_playertwo_score, roundvisual;
    Button[] button = new Button[9];
    Button  button_reset;
    int playeronescorecount, playertwoscorecount, roundcount, r;
    boolean activePlayer;

    int [] gameState ={2,2,2,2,2,2,2,2,2};
    int [][] winningStates ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8}, {0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);

        text_playerone_score =(TextView) findViewById(R.id.txt_playerone_score);
        text_playertwo_score =(TextView) findViewById(R.id.txt_playertwo_score);
        button_reset = (Button) findViewById(R.id.btn_reset);
        roundvisual = (TextView) findViewById(R.id.txt_roundcnt);

        for (int i=0; i < button.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            button[i] = (Button) findViewById(resourceID);
            button[i].setOnClickListener(this);
        }
        roundcount = 0;
        playeronescorecount = 0;
        playertwoscorecount = 0;
        r =1;
        activePlayer = true;
    }
    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#FFFFFF"));
            gameState[gameStatePointer] = 0;
        }else{
            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#FFFF00"));
            gameState[gameStatePointer] = 1;
        }
        roundcount++;
        if(checkWinner()){
            if(activePlayer){
                Toast.makeText(this, "Player One Won", Toast.LENGTH_SHORT).show();
                playeronescorecount++;
                r++;
                updatePlayerScore();
                playAgain();
                roundviz();
            }else{
                Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();
                playertwoscorecount++;
                r++;
                updatePlayerScore();
                playAgain();
                roundviz();

            }
        }else if(roundcount == 9){
            playAgain();
            roundviz();
            r++;
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();

        }else{
            activePlayer = !activePlayer;
        }
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                r = 1;
                playeronescorecount = 0;
                playertwoscorecount = 0;
                updatePlayerScore();
                roundviz();
            }
        });
    }
    public boolean checkWinner(){
        boolean winnerResult = false;
        for(int [] winningState:winningStates){
            if(gameState[winningState[0]]==gameState[winningState[1]] &&
                gameState[winningState[1]]==gameState[winningState[2]] &&
                    gameState[winningState[0]] !=2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerScore(){
        text_playerone_score.setText(Integer.toString(playeronescorecount));
        text_playertwo_score.setText(Integer.toString(playertwoscorecount));
    }
    public void playAgain(){
        roundcount = 0;
        activePlayer = true;
        for(int i = 0; i < button.length; i++){
            gameState[i] = 2;
            button[i].setText("");
        }
    }
    public void roundviz(){
        roundvisual.setText(Integer.toString(r));
    }
}