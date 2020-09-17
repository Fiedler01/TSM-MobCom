package ch.bfh.ti.cloudexercise;

import android.app.Activity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SingleActivity extends Activity {
    private DatabaseReference valueReference;
    private EditText inputEditText;
    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* setup activity */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        /* get UI elements */
        inputEditText = findViewById(R.id.single_edit);
        outputTextView = findViewById(R.id.single_text);

        /* get a database reference to the value */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        valueReference = database.getReference("single");

        registerValueChangeListener();
    }

    private void registerValueChangeListener() {
        /* ===== START OF CODE FOR EXERCISE 1.2 ===== */
        valueReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                outputTextView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("CloudExercise", "Database Error", databaseError.toException());
            }
        });
        /* ===== END OF CODE FOR EXERCISE 1.2 ===== */
    }

    /*
     * button event handlers
     */

    public void onUpdateValueButtonClicked(View v) {
        String text = inputEditText.getText().toString();

        /* ===== START OF CODE FOR EXERCISE 1.1 ===== */
        valueReference.setValue(text);
        /* ===== END OF CODE FOR EXERCISE 1.1 ===== */
    }
}
