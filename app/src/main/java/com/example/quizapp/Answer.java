package com.example.quizapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Answer extends AppCompatActivity {

    private int optionA, optionB, optionC, optionD, question_id, answer_id;

    public Answer(int questionid, int answerid, int option_a, int option_b, int option_c, int option_d) {
        question_id = questionid;
        optionA = option_a;
        optionB = option_b;
        optionC = option_c;
        optionD = option_d;
        answer_id = answerid;
    }

    public int getOptionA() {
        return optionA;
    }

    public int getOptionB() {
        return optionB;
    }

    public int getOptionC() {
        return optionC;
    }

    public int getOptionD() {
        return optionD;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }
}
