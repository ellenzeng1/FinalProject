package com.zengellen.finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    public int counter;
    Button button;
    TextView textView;

    DrawView mDrawView;
    private int currentScore = 0;
    int[] indices;
    int count = -1;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.activity_main);
        indices = new int[]{R.layout.page1, R.layout.page2, R.layout.page3};

        count=(++count)%indices.length;
        //page 1
        if(count==0) {
            setContentView(R.layout.page1);
            mDrawView = findViewById(R.id.drawView);
        }

        //page2
        if(count==1) {
            setContentView(R.layout.page2);
            button = (Button) findViewById(R.id.startbutton);
            textView = (TextView) findViewById(R.id.time);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new CountDownTimer(30000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            textView.setText(String.valueOf(counter));
                            counter++;
                        }

                        public void onFinish() {
                            textView.setText("FINISH!!");
                        }
                    }.start();
                }
            });
        }

        //page3
        if(count==2) {
            setContentView(R.layout.page3);
            ImageView cookiepic = (ImageView) findViewById(R.id.cookiepic);
            cookiepic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentScore++;
                    Toast.makeText(MainActivity.this, "your score is " + currentScore, Toast.LENGTH_SHORT).show();
                    TextView test = (TextView) findViewById(R.id.totalcount);
                    test.setText(String.valueOf(currentScore));
                }
            });
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDrawView.pause();
    }

    /**
     * Resumes game when activity is resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();
        mDrawView.resume();
    }
}