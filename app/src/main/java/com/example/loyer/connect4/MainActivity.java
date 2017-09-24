package com.example.loyer.connect4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    //yellow=0,red=1
    int activePlayer;
    boolean gameisActive=true;
    // 2 means unplayed empty? or full?
    int [] gameState = {2,2,2,2,2,2,2,2,2};
    // winning position
    int [][] winningPositions =  {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view)
    {
        ImageView counter= (ImageView) view;


        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameisActive) {
            gameState[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            // check position
            // check the control position
            for(int[] winningPosition : winningPositions )
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]]&&
                        gameState[winningPosition[1]]==gameState[winningPosition[2]]&&
                        gameState[winningPosition[0]]!=2){
                    System.out.println(gameState[winningPosition[0]]);
                    // someone has won!

                    gameisActive=false;
                    LinearLayout layou = (LinearLayout)findViewById(R.id.lnlPlayAgain);
                    layou.setVisibility(View.VISIBLE);
                    TextView winnerMsg = (TextView) findViewById(R.id.txtmsgWinner);
                    if(activePlayer==0)
                    {
                        winnerMsg.setText("Red has won!");
                    }else if(activePlayer==1)
                    {
                        winnerMsg.setText("Yellow has won!");
                    }// İt is a draw?
                } else{

                    boolean gameisOver=true;
                    for(int counterState:gameState){
                        if(counterState==2)gameisOver=false;
                    }
                    if(gameisOver)
                    {
                        TextView winnerMsg = (TextView) findViewById(R.id.txtmsgWinner);
                        winnerMsg.setText("It is a draw");
                        LinearLayout layou = (LinearLayout)findViewById(R.id.lnlPlayAgain);
                        layou.setVisibility(View.VISIBLE);


                    }


                }
            }
        }

    }
    public void playAgain(View view)
    {
        gameisActive=true;
        // layout is invisible
        LinearLayout layou=(LinearLayout)findViewById(R.id.lnlPlayAgain);
        layou.setVisibility(View.INVISIBLE);
        // gamestate is set the old default
        activePlayer=0;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        // the images on  grid have been deleted.
        GridLayout grid = (GridLayout)findViewById(R.id.grdBoard);

        for(int i=0;i<grid.getChildCount();i++){
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
