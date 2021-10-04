package com.example.dg_basicboxtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class BoxingRoundTimerActivity extends AppCompatActivity {

    private EditText mEditTextBRT;
    private Button  ButtonAdder;
    private Button ButtonSubtract;
    private Button homeButton;
    private Button startButton;
    private String EditTextString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boxing_round_timer);

        mEditTextBRT=findViewById(R.id.NumberOfRounds);
        ButtonAdder=findViewById(R.id.adder_button);
        ButtonSubtract=findViewById(R.id.subtract_button);
        homeButton=findViewById(R.id.home_button);
        startButton=findViewById(R.id.play_button);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(BoxingRoundTimerActivity.this,TimerActivity.class);
                EditTextString=mEditTextBRT.getText().toString();
                i.putExtra("Value",EditTextString); //Broj rundi
                i.putExtra("Rest_time","60000"); // DEFAULT TRAJANJE ODMORA
                i.putExtra("Active_time","180000");// DEFAULT TRAJANJE RUNDE
                if(Integer.parseInt(mEditTextBRT.getText().toString())==0){
                openEqual0Dialog();

                }
                else{
                    startActivity(i);
                }
                finish();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityActivityMain();
            }
        });


        ButtonAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditTextBRT.getText().toString().length()== 0){
                    mEditTextBRT.setText("1");
                }
                int num=Integer.parseInt(mEditTextBRT.getText().toString());
                num++;
                mEditTextBRT.setText(String.valueOf(num));
            }
        });

        ButtonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditTextBRT.getText().toString().length()== 0){
                    mEditTextBRT.setText("1");
                }
                int num=Integer.parseInt(mEditTextBRT.getText().toString());
                num--;
                if(num< 0){
                    openDialog();
                    num=0;
                }
                mEditTextBRT.setText(String.valueOf(num));
            }
        });

    }

    private void openEqual0Dialog() {
        Equal0Dialog dialog1 = new Equal0Dialog();
        dialog1.show(getSupportFragmentManager(), "equal 0 dialog");
    }

    private void openActivityTimerActivity() {
        Intent intent = new Intent(this, TimerActivity.class);
        startActivity(intent);
    }

    private void openActivityActivityMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openDialog() {
        LessThanZeroDialog dialog1 = new LessThanZeroDialog();
        dialog1.show(getSupportFragmentManager(), "example dialog");
    }
}