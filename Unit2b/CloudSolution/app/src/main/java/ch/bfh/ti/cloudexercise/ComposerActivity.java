package ch.bfh.ti.cloudexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.Objects;

public class ComposerActivity extends Activity {
    private DatabaseReference messageReference;
    private EditText contentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* setup activity */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer);

        /* get UI elements */
        contentEditText = findViewById(R.id.composer_content);

        /* get a database reference to the message object */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        messageReference = database.getReference(Objects.requireNonNull(getIntent().getStringExtra("URL")));
    }

    /*
     * button event handlers
     */

    /* used in activity_composer.xml */
    public void onPostMessageButtonClicked(View v) {
        /* create a message object */
        String content = contentEditText.getText().toString();
        Date date = new Date();
        int upvotes = 0;
        Message message = new Message(content, date, upvotes);

        /* ===== START OF CODE FOR EXERCISE 1.2 ===== */
        messageReference.setValue(message);
        /* ===== END OF CODE FOR EXERCISE 1.2 ===== */

        /* terminate this activity after posting the message */
        finish();
    }
}
