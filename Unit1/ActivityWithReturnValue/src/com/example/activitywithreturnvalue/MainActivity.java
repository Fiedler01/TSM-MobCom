package com.example.activitywithreturnvalue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final int MY_REQUEST_CODE = 23; // Arbitrary number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startQuestionActivity(View v) {
        // Call QuestionActivity with question
        Intent questionCall = new Intent(this, QuestionActivity.class);
        questionCall.putExtra("question1",  "What is your name?");
        questionCall.putExtra("question2", "How old are you?");
        startActivityForResult(questionCall, MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Use return data and print to log
        if (requestCode == MY_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data.hasExtra("answer1")) {
                TextView textView = findViewById(R.id.main_textView_result);
                String answer = getResources().getString(R.string.main_text_name) + "'" + data.getExtras().getString("answer1") + "'";
                textView.setText(answer);
            }
            if (data.hasExtra("answer2")) {
                TextView textView = findViewById(R.id.main_textView_result_age);
                String answer = getResources().getString(R.string.main_text_age) + "'" + data.getExtras().getString("answer2") + "'";
                textView.setText(answer);
            }
        }
    }
}
