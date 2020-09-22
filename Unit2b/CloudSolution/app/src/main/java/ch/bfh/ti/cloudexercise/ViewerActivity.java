package ch.bfh.ti.cloudexercise;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class ViewerActivity extends Activity {
    private DatabaseReference messageReference;
    private Message message;

    private TextView contentTextView;
    private TextView dateTextView;
    private TextView upvoteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* setup activity */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer);

        /* get UI elements */
        upvoteTextView = findViewById(R.id.message_upvotes);
        contentTextView = findViewById(R.id.message_content);
        dateTextView = findViewById(R.id.message_date);

        /* get a database reference to the message */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        messageReference = database.getReference(Objects.requireNonNull(getIntent().getStringExtra("URL")));

        registerValueEventListener();
    }

    private void registerValueEventListener() {
        /* ===== START OF CODE FOR EXERCISE 2.2 ===== */
        messageReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                message = dataSnapshot.getValue(Message.class);
                showMessage();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("CloudExercise", "Database Error", databaseError.toException());
            }
        });
        /* ===== END OF CODE FOR EXERCISE 2.2 ===== */
    }

    private void showMessage() {
        upvoteTextView.setText(String.valueOf(message.getUpvotes()));
        contentTextView.setText(message.getContent());
        dateTextView.setText(String.valueOf(message.getDate()));
    }

    /*
     * button callbacks
     */

    public void onUpvoteButtonClicked(View v) {
        /* ===== START OF CODE FOR EXERCISE 2.3 and 2.4 ===== */
        // solution to exercise 2.4 with transaction
        messageReference.child("upvotes").runTransaction(new Transaction.Handler() {
            @NonNull
            @Override
            public Transaction.Result doTransaction(@NonNull MutableData mutableData) {
                Integer upvotes = mutableData.getValue(Integer.class);

                if (upvotes == null) {
                    return Transaction.success(mutableData);
                }

                mutableData.setValue(upvotes + 1);

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                if (databaseError != null) {
                    Log.w("CloudExercise", "Database Error", databaseError.toException());
                }
            }
        });
        /* ===== END OF CODE FOR EXERCISE 2.3 and 2.4 ===== */
    }
}
