package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView optionA, optionB, optionC, optionD;
    private TextView question_number, question, score;
    private TextView checkingAnswer1, checkingAnswer2;
    int currentIndex;
    int mscore = 0;
    int qn = 1;
    ProgressBar progressBar;

    int CurrentQuestion, CurrentOptionA, CurrentOptionB, CurrentOptionC, CurrentOptionD;


    private Answer[] questionBank = new Answer[] {
            new Answer(R.string.question_1,R.string.question1_A,R.string.question1_B,R.string.question1_C,R.string.question1_D,R.string.answer_1),
            new Answer(R.string.question_2,R.string.question2_A,R.string.question2_B,R.string.question2_C,R.string.question2_D,R.string.answer_2),
            new Answer(R.string.question_3,R.string.question3_A,R.string.question3_B,R.string.question3_C,R.string.question3_D,R.string.answer_3),
            new Answer(R.string.question_4,R.string.question4_A,R.string.question4_B,R.string.question4_C,R.string.question4_D,R.string.answer_4),
            new Answer(R.string.question_5,R.string.question5_A,R.string.question5_B,R.string.question5_C,R.string.question5_D,R.string.answer_5),
            new Answer(R.string.question_6,R.string.question6_A,R.string.question6_B,R.string.question6_C,R.string.question6_D,R.string.answer_6),
            new Answer(R.string.question_7,R.string.question7_A,R.string.question7_B,R.string.question7_C,R.string.question7_D,R.string.answer_7),
            new Answer(R.string.question_8,R.string.question8_A,R.string.question8_B,R.string.question8_C,R.string.question8_D,R.string.answer_8)

    };

    final int PROGRESS_BAR = (int) Math.ceil(100/questionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        optionA = (TextView) findViewById(R.id.optionA);
        optionB = (TextView) findViewById(R.id.optionB);
        optionC = (TextView) findViewById(R.id.optionC);
        optionD = (TextView) findViewById(R.id.optionD);

        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        question_number = findViewById(R.id.QuestionNumber);
        checkingAnswer1 = findViewById(R.id.selectoption);
        checkingAnswer2 = findViewById(R.id.CorrectAnswer);
        progressBar = findViewById(R.id.progress_bar);

        CurrentQuestion = questionBank[currentIndex].getQuestion_id();
        question.setText(CurrentQuestion);
        CurrentOptionA = questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB = questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC = questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD = questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionA);
                updateQuestion();
            }
        });

        optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionB);
                updateQuestion();
            }
        });

        optionC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionC);
                updateQuestion();
            }
        });

        optionD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(CurrentOptionD);
                updateQuestion();
            }
        });
    }

    private void checkAnswer(int userSelection) {

        int correctanswer = questionBank[currentIndex].getAnswer_id();
        checkingAnswer1.setText(userSelection);
        checkingAnswer2.setText(correctanswer);

        String m = checkingAnswer1.getText().toString().trim();
        String n = checkingAnswer2.getText().toString().trim();

        if (m.equals(n)) {
            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
            mscore = mscore + 1;
        }
        else {
            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateQuestion() {

        currentIndex = (currentIndex + 1) % questionBank.length;

        if (currentIndex == 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setCancelable(false);
            alert.setMessage("Your Score" + mscore + "points");
            alert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mscore = 0;
                    qn  = 1;
                    progressBar.setProgress(0);
                    score.setText("Score" + mscore + "/" + questionBank.length);
                    question_number.setText(qn + "/" + questionBank.length + "Question");
                }
            });
            alert.show();
        }

        CurrentQuestion = questionBank[currentIndex].getQuestion_id();
        question.setText(CurrentQuestion);
        CurrentOptionA = questionBank[currentIndex].getOptionA();
        optionA.setText(CurrentOptionA);
        CurrentOptionB = questionBank[currentIndex].getOptionB();
        optionB.setText(CurrentOptionB);
        CurrentOptionC = questionBank[currentIndex].getOptionC();
        optionC.setText(CurrentOptionC);
        CurrentOptionD = questionBank[currentIndex].getOptionD();
        optionD.setText(CurrentOptionD);

        qn = qn + 1;

        if (qn <= questionBank.length) {
            question_number.setText(qn + "/" + questionBank.length + "Question");
        }
        score.setText("Score" + mscore + "/" + questionBank.length);
        progressBar.incrementProgressBy(PROGRESS_BAR);

    }
}