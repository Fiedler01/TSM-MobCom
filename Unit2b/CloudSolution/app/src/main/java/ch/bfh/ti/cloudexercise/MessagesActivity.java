package ch.bfh.ti.cloudexercise;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends ListActivity {
    private final List<KeyMessagePair> keyMessagePairs = new ArrayList<>();
    private MessageAdapter adapter;
    private DatabaseReference messagesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* setup activity */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        /* create and set the adaptor for the list view */
        adapter = new MessageAdapter(this, keyMessagePairs);
        setListAdapter(adapter);

        /* get a database reference to the message list */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        messagesReference = database.getReference("messages");

        registerChildEventListener();
    }

    private void registerChildEventListener() {
        /* Query is a superclass of Reference. */
        Query query = messagesReference;

        /* ===== START OF CODE FOR EXERCISE 3.3 ===== */
        query = query.orderByChild("upvotes").limitToLast(10);
        /* ===== END OF CODE FOR EXERCISE 3.3 ===== */

        /* create a database listener for changes in the list */
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) {
                /* ===== START OF CODE FOR EXERCISE 3.2 ===== */
                String key = dataSnapshot.getKey();
                Message message = dataSnapshot.getValue(Message.class);

                keyMessagePairs.add(new KeyMessagePair(key, message));
                /* ===== END OF CODE FOR EXERCISE 3.2 ===== */

                /* notify the adaptor that the underlying data has changed */
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String previousChildKey) {
                /* ===== START OF CODE FOR EXERCISE 3.3 ===== */
                String key = dataSnapshot.getKey();
                int index = indexOfMessageWithKey(key);

                Message message = dataSnapshot.getValue(Message.class);
                keyMessagePairs.set(index, new KeyMessagePair(key, message));
                /* ===== END OF CODE FOR EXERCISE 3.3 ===== */

                /* notify the adaptor that the underlying data has changed */
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                /* ===== START OF CODE FOR EXERCISE 3.3 ===== */
                String key = dataSnapshot.getKey();
                int index = indexOfMessageWithKey(key);

                keyMessagePairs.remove(index);
                /* ===== END OF CODE FOR EXERCISE 3.3 ===== */

                /* notify the adaptor that the underlying data has changed */
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String previousChildKey) {
                /* ===== START OF CODE FOR EXERCISE 3.3 ===== */
                /* remove pair from old position */
                String key = dataSnapshot.getKey();
                int oldIndex = indexOfMessageWithKey(key);
                KeyMessagePair pair = keyMessagePairs.remove(oldIndex);

                /* insert or append pair at new position */
                int newIndex = indexOfMessageWithKey(previousChildKey) + 1;
                if (newIndex == keyMessagePairs.size()) {
                    keyMessagePairs.add(pair);
                } else {
                    keyMessagePairs.add(newIndex, pair);
                }
                /* ===== END OF CODE FOR EXERCISE 3.3 ===== */

                /* notify the adaptor that the underlying data has changed */
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("CloudExercise", "Database Error", databaseError.toException());
            }
        });
    }

    /* returns the index of the message given its key in the keyMessagePairs list
     * or -1 if the key cannot be found */
    private int indexOfMessageWithKey(String key) {
        for (int index = 0; index < keyMessagePairs.size(); ++index) {
            if (keyMessagePairs.get(index).getKey().equals(key)) {
                return index;
            }
        }

        return -1;
    }

    /*
     * button event handlers
     */

    public void onNewMessageButtonClicked(View v) {
        String newMessageKey;

        /* ===== START OF CODE FOR EXERCISE 3.1 ===== */
        newMessageKey = messagesReference.push().getKey();
        /* ===== END OF CODE FOR EXERCISE 3.1 ===== */

        Intent intent = new Intent(this, ComposerActivity.class);
        intent.putExtra("URL", "messages/" + newMessageKey);
        startActivity(intent);
    }

    /*
     * list event handlers
     */

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(this, ViewerActivity.class);
        intent.putExtra("URL", "messages/" + adapter.getItem(position).getKey());
        startActivity(intent);
    }
}
