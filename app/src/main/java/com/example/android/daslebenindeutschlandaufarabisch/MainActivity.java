package com.example.android.daslebenindeutschlandaufarabisch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;



// Declaring the CheckedTextView

    CheckedTextView answer1, answer2, answer3, answer4;

// Declaring the TextViews

    TextView scoreV, question, questionAr;

// Allow using Question class

    private Questions mQuestions = new Questions();

// Declaring correct answer, score variables

    private String correctAnswer;

    // To get the array of the Questions
    private int mQuestionsLength = mQuestions.mQuestions.length;

    // Declaring a random variable
    Random r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ad Mob Adds
        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, "ca-app-pub-4358846727124158~5559029198");

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        r = new Random();


// Defining Views by id
        answer1 =  findViewById(R.id.answer1);
        answer1.setMovementMethod(new ScrollingMovementMethod());
        answer2 =  findViewById(R.id.answer2);
        answer2.setMovementMethod(new ScrollingMovementMethod());
        answer3 =  findViewById(R.id.answer3);
        answer3.setMovementMethod(new ScrollingMovementMethod());
        answer4 =  findViewById(R.id.answer4);
        answer4.setMovementMethod(new ScrollingMovementMethod());

        scoreV =  findViewById(R.id.score);
        question =  findViewById(R.id.question);
        question.setMovementMethod(new ScrollingMovementMethod());
        questionAr =  findViewById(R.id.questionAr);
        questionAr.setMovementMethod(new ScrollingMovementMethod());

// Set score text
        scoreV.setText(getString(R.string.score, mQuestions.getScore()));

// Update views to take the new question
        updateQuestion (r.nextInt(mQuestionsLength));

// On Click  Choice 1
        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (answer1.getText().toString().equalsIgnoreCase(correctAnswer) ){
                    mQuestions.addScore();
                    scoreV.setText(getString(R.string.score, mQuestions.getScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mQuestions.addFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });


// On Click - Choice 2
        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (answer2.getText().toString().equalsIgnoreCase(correctAnswer)){
                    mQuestions.addScore();
                    scoreV.setText(getString(R.string.score, mQuestions.getScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mQuestions.addFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });


// On Click - Choice 3
        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (answer3.getText().toString().equalsIgnoreCase(correctAnswer)){
                    mQuestions.addScore();
                    scoreV.setText(getString(R.string.score, mQuestions.getScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mQuestions.addFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });




// On Click - Choice 4
        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (answer4.getText().toString().equalsIgnoreCase(correctAnswer)){
                    mQuestions.addScore();
                    scoreV.setText(getString(R.string.score, mQuestions.getScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    mQuestions.addFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });
    }



// Update views to take the new question
    private void updateQuestion(int num) {
        question.setText(mQuestions.getQuestion(num));
        questionAr.setText(mQuestions.getQuestionAr(num));
        answer1.setText(mQuestions.getChoice1(num));
        answer2.setText(mQuestions.getChoice2(num));
        answer3.setText(mQuestions.getChoice3(num));
        answer4.setText(mQuestions.getChoice4(num));
        correctAnswer = mQuestions.getCorrectAnswer(num);
    }

    // Right answer message
    private void setRightMessageToast(){
        Toast trueAnswerMessage = Toast.makeText(getApplicationContext(),getString(R.string.rightAnswer),Toast.LENGTH_SHORT);
        trueAnswerMessage.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
        trueAnswerMessage.setGravity(Gravity.TOP,0,200);
        trueAnswerMessage.show();
    }

    // Wrong answer message
    private void setWrongMessageToast (){
        Toast wrongAnswerToast = new Toast(this);
        wrongAnswerToast.setGravity(Gravity.CENTER,0,0);
        wrongAnswerToast.setDuration(Toast.LENGTH_SHORT);
        LayoutInflater wrongToastInflater = getLayoutInflater();
        View inflaterView = wrongToastInflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.wrong_message_layout));
        wrongAnswerToast.setView(inflaterView);
        wrongAnswerToast.show();
    }


    // New Game
    public void newGame(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage(getString(R.string.newGameMessage, mQuestions.getScore(),mQuestions.getFailedAttempts()))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.newGameButton),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                mQuestions.resetScore();
                                mQuestions.resetFailedAttempts();
                                finish();
                            }
                        })

                .setNegativeButton(getString(R.string.backButton),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


// Close the App

    public void closeApp(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder
                .setMessage(getString(R.string.closeAppMessage, mQuestions.getScore(),mQuestions.getFailedAttempts()))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.exitButton),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })

                .setNegativeButton(getString(R.string.backButton),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


// Go to home screen

    public void goHome(View view){
        Intent mainIntent = new Intent(this, HomeActivity.class);
        startActivity(mainIntent);
    }


}


