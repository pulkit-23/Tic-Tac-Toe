package com.example.mysecondapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity<savedInstanceState> extends AppCompatActivity {
    boolean gameActive = true;
    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2 , 2, 2, 2, 2, 2, 2, 2};
    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    private Object savedInstanceState;
    int count=0,flag=0;
    public void playerTap(View view){
//            if(count==9){
//                gameReset(view);
//            }
            ImageView img = (ImageView) view;
            int tappedImage = Integer.parseInt(img.getTag().toString());

            if(gameState[tappedImage] == 2) {
                count+=1;
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x3);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("O's Turn - Tap to play");
                } else {
                    img.setImageResource(R.drawable.o2);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("X's Turn - Tap to play");
                }
                img.animate().translationYBy(1000f).setDuration(300);
            }
            if(!gameActive){
                gameReset(view);
            }
            // Check if any player has won
            for(int[] winPosition: winPositions){
                if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]]!=2){
                    // Somebody has won! - Find out who!
                    String winnerStr;
                    flag=1;
                    gameActive = false;
                    if(gameState[winPosition[0]] == 0){
                        winnerStr = "X has won";
                    }
                    else{
                        winnerStr = "O has won";
                    }
                    // Update the status bar for winner announcement
                    TextView status = findViewById(R.id.status);
                    status.setText(winnerStr);

                }
            }
            if(count==9&&flag==0){
                gameActive=false;
                TextView status = findViewById(R.id.status);
                status.setText("Tie!");
            }
    }

        public void gameReset(View view){
            gameActive = true;
            count=0;
            flag=0;
            activePlayer = 0;
            for(int i=0; i<gameState.length; i++){
                gameState[i] = 2;
            }
            ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView11)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView10)).setImageResource(0);
            ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);

            TextView status = findViewById(R.id.status);
            status.setText("X's Turn - Tap to play");

        }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate((android.os.Bundle) savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
