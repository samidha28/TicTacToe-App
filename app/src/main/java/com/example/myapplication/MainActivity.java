package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    TextView HeaderText;

    int player_o = 0;
    int player_x = 1;

    int activePlayer = player_o;

    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean gameActive=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HeaderText= findViewById(R.id.header1_text);
        btn0= findViewById(R.id.btn0);
        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        btn3= findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);
        btn5= findViewById(R.id.btn5);
        btn6= findViewById(R.id.btn6);
        btn7= findViewById(R.id.btn7);
        btn8= findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        if(!gameActive)
            return;

        Button clickedBtn = findViewById(v.getId());
        int clickedTag= Integer.parseInt(v.getTag().toString());

        if(filledPos[clickedTag]!=-1)
            return;

        filledPos[clickedTag] = activePlayer;

        if(activePlayer==player_o){
            clickedBtn.setText("O");
            activePlayer=player_x;
            HeaderText.setText("X's turn");
            //clickedBtn.setBackgroundTintList(ColorStateList.valueOf(getDrawable().getColor(R.color.white));
        }
        else{
            clickedBtn.setText("X");
            activePlayer=player_o;
            HeaderText.setText(" O's turn ");
            //clickedBtn.setBackgroundResource(android.R.color.holo_orange_light);
        }

        check();
    }

    private void check(){
        int [][] winPos={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        for(int i=0;i<8;i++){

            int v0= winPos[i][0];
            int v1= winPos[i][1];
            int v2= winPos[i][2];

            if(filledPos[v0]==filledPos[v1] && filledPos[v1]==filledPos[v2]) {
                if(filledPos[v0]!=-1){
                    if(filledPos[v0]==player_o) {
                        show("O is winner");
                        return;
                    }
                    else {
                        show("X is winner");
                        return;
                    }
                }
            }
        }
        int count=0;
        for(int i=0;i<9;i++) {
            if(filledPos[i] != -1) {
                count++;
            }
        }
        if(count==9) {
            show(" Match is drawn");
        }
    }

    private void show(String winnerText){
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void restartGame(){
        activePlayer= player_o;
        filledPos= new int[]{-1, -1, -1, -1, -1, -1, -1, -1 ,-1};

        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        /*btn0.setBackground(getDrawable(android.R.color.darker_gray));
        btn1.setBackground(getDrawable(android.R.color.darker_gray));
        btn2.setBackground(getDrawable(android.R.color.darker_gray));
        btn3.setBackground(getDrawable(android.R.color.darker_gray));
        btn4.setBackground(getDrawable(android.R.color.darker_gray));
        btn5.setBackground(getDrawable(android.R.color.darker_gray));
        btn6.setBackground(getDrawable(android.R.color.darker_gray));
        btn7.setBackground(getDrawable(android.R.color.darker_gray));
        btn8.setBackground(getDrawable(android.R.color.darker_gray));*/

        HeaderText.setText("O turn");
    }
}