package ch.bfh.ti.cloudexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* setup activity */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSingleActivity(View view) {
        Intent intent = new Intent(this, SingleActivity.class);
        startActivity(intent);
    }

    public void startComposerActivity(View view) {
        Intent intent = new Intent(this, ComposerActivity.class);
        intent.putExtra("URL", "object");
        startActivity(intent);
    }

    public void startViewerActivity(View view) {
        Intent intent = new Intent(this, ViewerActivity.class);
        intent.putExtra("URL", "object");
        startActivity(intent);
    }

    public void startMessagesActivity(View view) {
        Intent intent = new Intent(this, MessagesActivity.class);
        startActivity(intent);
    }
}
