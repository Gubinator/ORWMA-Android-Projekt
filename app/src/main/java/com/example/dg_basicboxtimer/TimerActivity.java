package com.example.dg_basicboxtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {

    private TextView countdownText;
    private CountDownTimer countDownTimer;
    private Button coundownButton;
    private long timeLeftInMiliseconds = 3*60*1000; //3 minutes boxing round
    private boolean timerRunning;
    private TextView passedRounds;
    private String passedRoundsString;
    private Integer passedRoundsInt;
    private TextView timer_txt;
    private long REST_TIME;
    private long ACTIVE_TIME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        countdownText = findViewById(R.id.countdown_timer);
        coundownButton = findViewById(R.id.countdown_button);

        passedRounds = findViewById(R.id.text_rounds_left);
        passedRoundsString = getIntent().getExtras().getString("Value");
        REST_TIME = Long.parseLong(getIntent().getExtras().getString("Rest_time"));
        ACTIVE_TIME = Long.parseLong(getIntent().getExtras().getString("Active_time"));
        timeLeftInMiliseconds=ACTIVE_TIME;
        passedRoundsInt = Integer.parseInt(passedRoundsString);
        passedRounds.setText(String.valueOf(passedRoundsInt));

        timer_txt=findViewById(R.id.timer_timer_txt);
        Long START_VALUE_TEXT_MIN = Long.valueOf(ACTIVE_TIME)/1000/60;
        Long START_VALUE_TEXT_SEC = Long.valueOf(ACTIVE_TIME)/1000%60;
        String START_VALUE_ADDED_ZERO_MIN = Utilities.addZero(START_VALUE_TEXT_MIN.toString());
        String START_VALUE_ADDED_ZERO_SEC = Utilities.addZero(START_VALUE_TEXT_SEC.toString());

        countdownText.setText(START_VALUE_ADDED_ZERO_MIN+":"+START_VALUE_ADDED_ZERO_SEC);
        coundownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startStop();
            }
        });
    }

    private void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        coundownButton.setText("START");
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMiliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMiliseconds = l;
                updateTimer();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        coundownButton.setText("PAUSE");
        timerRunning = true;
    }

    private boolean odmor = false;
    private void updateTimer() {
        int minutes = (int) timeLeftInMiliseconds / 60000;
        int seconds = (int) timeLeftInMiliseconds % 60000 / 1000;

        String time = Utilities.addZero(String.valueOf(minutes))+":"+Utilities.addZero(String.valueOf(seconds));

        countdownText.setText(time);
        if (time.equals("00:00")) { // ako je 0:00 onda runda broj -1 (compareaju se string s textviewom pa se koristi metoda equals)
            if (passedRoundsInt >= 1) {
                if(odmor==false) {
                    passedRoundsInt = passedRoundsInt - 1;
                    if(passedRoundsInt>0) {
                        timeLeftInMiliseconds = REST_TIME;
                        passedRounds.setText(String.valueOf(passedRoundsInt));
                        odmor = true;
                        timer_txt.setText("REST");
                        startTimer();
                    } else{
                        Intent intent = new Intent(this, DoneActivity.class);
                        startActivity(intent);}
                } else{
                    timeLeftInMiliseconds=ACTIVE_TIME;
                    odmor = false;
                    timer_txt.setText("TIMER");
                    startTimer();
                }

             }

        }
        }
    }
