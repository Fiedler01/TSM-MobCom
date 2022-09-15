package com.example.activitywithreturnvalue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Asks the user a question and returns the answer to the caller.
 */
public class QuestionActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        registerListeners();
        getExtras();

    }

    private void getExtras() {
        // Read parameters and show on view.
        String question = getIntent().getStringExtra("question1");
        setQuestionTextForAnswer1(question);
        String answer2 = getIntent().getStringExtra("question2");
        setQuestionTextForAnswer2(answer2);
    }

    @Override
    public void finish() {
        // Read answer and set result on view.
        String answer1 = getAnswer1();
        String answer2 = getAnswer2();

        Intent answerData = new Intent();
        answerData.putExtra("answer1", answer1);
        answerData.putExtra("answer2", answer2);
        setResult(RESULT_OK, answerData);

        super.finish();
    }

    private void registerListeners() {
        Button button = findViewById(R.id.question_button_done);
        button.setOnClickListener(v -> finish());
    }

    private void setQuestionTextForAnswer1(String question) {
        TextView textView = findViewById(R.id.question_label);
        textView.setText(question);
    }

    private void setQuestionTextForAnswer2(String question) {
        TextView textView = findViewById(R.id.question_label_answer2);
        textView.setText(question);
    }

    private String getAnswer1() {
        EditText editText = findViewById(R.id.question_text_answer1);
        return editText.getText().toString();
    }

    private String getAnswer2() {
        EditText editText = findViewById(R.id.question_text_answer2);
        return editText.getText().toString();
    }
}
