package com.example.eegreading;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Quiz extends AppCompatActivity {
    private com.example.eegreading.QuestionLibrary mQuestionLibrary = new com.example.eegreading.QuestionLibrary();
    private Dialog mDialog;
    private TextView mScoreView;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    private String mAnswer;
     int mScore = 0;
     int mQuestionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScoreView = (TextView)findViewById(R.id.score);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.not_at_all);
        mButtonChoice2 = (Button)findViewById(R.id.several_days);
        mButtonChoice3 = (Button)findViewById(R.id.more_than_half_the_days);
        mButtonChoice4 = (Button)findViewById(R.id.nearly_every_day);
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.popup3);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mDialog.show();

        updateQuestion();
        /*if (mQuestionNumber == 9){
            Intent intent = new Intent(this, QuizResult.class);
            startActivity(intent);
        }*/

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here
                if (mButtonChoice1.getText() == "Not at all"){
                    mScore = mScore + 0;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    //This line of code is optiona
                    //   Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }else {

                    mScore = mScore + 0;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    updateQuestion();
                    //  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here
                if (mButtonChoice2.getText() == "Several Days"){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    //This line of code is optiona
                    //   Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }else {

                    mScore = mScore + 1;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    updateQuestion();
                    //  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here
                if (mButtonChoice3.getText() == "More than half the days"){
                    mScore = mScore + 2;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    //This line of code is optiona
                    //   Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }else {

                    mScore = mScore + 2;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    updateQuestion();
                    //  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //End of Button Listener for Button3

        mButtonChoice4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here
                if (mButtonChoice4.getText() == "Nearly every day"){
                    mScore = mScore + 3;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    //This line of code is optiona
                    //   Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();
                }else {

                    mScore = mScore + 3;
                    updateScore(mScore);
                    if (mQuestionNumber == QuestionLibrary.mQuestions.length){
                        Intent i = new Intent(Quiz.this, QuizResult.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("Final Score", mScore);
                        i.putExtras(bundle);
                        Quiz.this.finish();
                        startActivity(i);
                    }else {
                        updateQuestion();
                    }
                    updateQuestion();
                    //  Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    private void updateQuestion(){
        mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
        mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
        mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
        mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));
        mButtonChoice4.setText(mQuestionLibrary.getChoice4(mQuestionNumber));

        ///   mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);




        mQuestionNumber++;
    }


    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

}