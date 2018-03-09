package com.example.android.daslebenindeutschlandaufarabisch;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ImagesTestActivity extends AppCompatActivity {


// Declaring the CheckedTextView

    CheckedTextView imagesAnswer1, imagesAnswer2, imagesAnswer3, imagesAnswer4;

    // Declaring the ImageView
    ImageView imageV;

// Declaring the TextViews

    TextView imagesScoreV, imagesQuestion, imagesQuestionAr;

// Allow using Question class
    // Declaring a random variable
    Random r;

// Declaring correct answer, score variables
    private Questions mQuestionsClass = new Questions();
    private String imagesCorrectAnswer;

    // To get the array of the Questions
    private int mImagesQuestionsLength = mQuestionsClass.mImagesQuestions.length;

    int [] imageNR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_test);

        r = new Random();

// Defining Views by id

        imageV = findViewById(R.id.image_view);
        imagesAnswer1 = findViewById(R.id.images_answer1);
        imagesAnswer2 = findViewById(R.id.images_answer2);
        imagesAnswer3 = findViewById(R.id.images_answer3);
        imagesAnswer4 = findViewById(R.id.images_answer4);

        imagesScoreV = findViewById(R.id.images_score);
        imagesQuestion = findViewById(R.id.images_question);
        imagesQuestionAr = findViewById(R.id.images_questionAr);

        imageNR = new int[]{R.drawable.images_test_0,R.drawable.images_test_1,R.drawable.images_test_2,R.drawable.images_test_3,R.drawable.images_test_4,R.drawable.images_test_5,R.drawable.images_test_6,R.drawable.images_test_7,R.drawable.images_test_8,R.drawable.images_test_9};


// Set score text
        imagesScoreV.setText(getString(R.string.score, mQuestionsClass.getImagesScore()));

// Update views to take the new question
        updateQuestion(r.nextInt(mImagesQuestionsLength));

// On Click  Choice 1
        imagesAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesAnswer1.getText() == imagesCorrectAnswer) {
                    mQuestionsClass.addImagesScore();
                    imagesScoreV.setText(getString(R.string.score, mQuestionsClass.getImagesScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mImagesQuestionsLength));
                } else {
                    mQuestionsClass.addImagesFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });


// On Click - Choice 2
        imagesAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesAnswer2.getText() == imagesCorrectAnswer) {
                    mQuestionsClass.addImagesScore();
                    imagesScoreV.setText(getString(R.string.score, mQuestionsClass.getImagesScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mImagesQuestionsLength));
                } else {
                    mQuestionsClass.addImagesFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });


// On Click - Choice 3
        imagesAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesAnswer3.getText() == imagesCorrectAnswer) {
                    mQuestionsClass.addImagesScore();
                    imagesScoreV.setText(getString(R.string.score, mQuestionsClass.getImagesScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mImagesQuestionsLength));
                } else {
                    mQuestionsClass.addImagesFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });


// On Click - Choice 4
        imagesAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imagesAnswer4.getText() == imagesCorrectAnswer) {
                    mQuestionsClass.addImagesScore();
                    imagesScoreV.setText(getString(R.string.score, mQuestionsClass.getImagesScore()));
                    setRightMessageToast();
                    updateQuestion(r.nextInt(mImagesQuestionsLength));
                } else {
                    mQuestionsClass.addImagesFailedAttempts();
                    setWrongMessageToast();
                }
            }
        });
    }


// Update views to take the new question
    private void updateQuestion(int num) {
        imageV.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), imageNR [num]));
        imagesQuestion.setText(mQuestionsClass.getImagesQuestion(num));
        imagesQuestionAr.setText(mQuestionsClass.getImagesQuestionAr(num));
        imagesAnswer1.setText(mQuestionsClass.getImagesChoice1(num));
        imagesAnswer2.setText(mQuestionsClass.getImagesChoice2(num));
        imagesAnswer3.setText(mQuestionsClass.getImagesChoice3(num));
        imagesAnswer4.setText(mQuestionsClass.getImagesChoice4(num));

        imagesCorrectAnswer = mQuestionsClass.getImagesCorrectAnswer(num);

    }


    // Right answer message
    private void setRightMessageToast() {
        Toast trueAnswerMessage = Toast.makeText(getApplicationContext(), getString(R.string.rightAnswer), Toast.LENGTH_SHORT);
        trueAnswerMessage.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
        trueAnswerMessage.setGravity(Gravity.TOP, 0, 200);
        trueAnswerMessage.show();
    }


    // Wrong answer message
    private void setWrongMessageToast() {
        Toast wrongAnswerToast = new Toast(this);
        wrongAnswerToast.setGravity(Gravity.CENTER, 0, 0);
        wrongAnswerToast.setDuration(Toast.LENGTH_SHORT);
        LayoutInflater wrongToastInflater = getLayoutInflater();
        View inflaterView = wrongToastInflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.wrong_message_layout));
        wrongAnswerToast.setView(inflaterView);
        wrongAnswerToast.show();
    }


// New Game
    public void newGame(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ImagesTestActivity.this);
        alertDialogBuilder
                .setMessage(getString(R.string.newGameMessage, mQuestionsClass.getImagesScore(), mQuestionsClass.getImagesFailedAttempts()))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.newGameButton),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), ImagesTestActivity.class));
                                mQuestionsClass.resetImagesScore();
                                mQuestionsClass.resetImagesFailedAttempts();
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
    public void closeApp(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ImagesTestActivity.this);
        alertDialogBuilder
                .setMessage(getString(R.string.closeAppMessage, mQuestionsClass.getImagesScore(), mQuestionsClass.getImagesFailedAttempts()))
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


