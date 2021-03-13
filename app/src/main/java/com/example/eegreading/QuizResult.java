package com.example.eegreading;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizResult extends AppCompatActivity {

    TextView mFinalScore ,mScoreResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        mFinalScore = (TextView)findViewById(R.id.score);
        mScoreResults = (TextView)findViewById(R.id.result);

        Bundle bundle = getIntent().getExtras();
        int score = bundle.getInt("Final Score");

        mFinalScore.setText(score + " /27");


        if (score<5){
            mScoreResults.setText("Your results suggest minimal symptoms of depression. While your symptoms are likely not having a major impact on your life, it may still be helpful to monitor them. It is recommended that you retake this test once every two weeks.");
        }
        else if (score>4 && score<10){
            mScoreResults.setText("Your result suggest that you may be experiencing some symptoms of mild depression. While your symptoms are likely not having a major impact on your life, it may still be helpful to monitor them. It is recommended that you retake this test once every two weeks.");

        }
        else if (score>10 && score<15){
            mScoreResults.setText("Your results suggest you may be suffering from moderate depression. While this is not a diagnosis test, it may be worthwhile to start a conversation with your doctor or mental health professional. Finding the right treatment plan can help you on the path toward feeling better. It is also recommended that you monitor your symptoms by retaking this test once every two weeks.");

        }
        else if (score>15 && score<20){
            mScoreResults.setText("Your results suggest that you may be suffering from moderately severe depression. While this is not a diagnosis test, people who scored similar to you typically receive a diagnosis major depression and have sought professional treatment for this disorder. It may be beneficial for you to consult your doctor or mental health professional immediately.");

        }
        else {
            mScoreResults.setText("Your results suggest that you may be suffering from severe depression. While this is not a diagnosis test, people who scored similar to you typically receive a diagnosis of major depression and have sought professional treatment for this disorder. It may be beneficial for you to consult your doctor or mental health professional immediately.");
        }
    }

    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}

