package com.example.activitylifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.activitylifecycle.util.StatusTracker;
import com.example.activitylifecycle.util.Utils;


/**
 * Example Activity to demonstrate the lifecycle callback methods.
 */
public class ActivityB extends Activity {

	private String mActivityName;
	private TextView mStatusView;
	private TextView mStatusAllView;
	private final StatusTracker mStatusTracker = StatusTracker.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_b);
		mActivityName = getString(R.string.activity_b_label);
		mStatusView = findViewById(R.id.status_view_b);
		mStatusAllView = findViewById(R.id.status_view_all_b);
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_create));
		Utils.printStatus(mStatusView, mStatusAllView);
	}

	@Override
	protected void onStart() {
		super.onStart();
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_start));
		Utils.printStatus(mStatusView, mStatusAllView);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_restart));
		Utils.printStatus(mStatusView, mStatusAllView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_resume));
		Utils.printStatus(mStatusView, mStatusAllView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_pause));
		Utils.printStatus(mStatusView, mStatusAllView);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_stop));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mStatusTracker.setStatus(mActivityName, getString(R.string.on_destroy));
	}

	public void startDialog(View v) {
		Intent intent = new Intent(ActivityB.this, com.example.activitylifecycle.DialogActivity.class);
		startActivity(intent);
	}

	public void startActivityA(View v) {
		Intent intent = new Intent(ActivityB.this, com.example.activitylifecycle.ActivityA.class);
		startActivity(intent);
	}

	public void startActivityC(View v) {
		Intent intent = new Intent(ActivityB.this, com.example.activitylifecycle.ActivityC.class);
		startActivity(intent);
	}

	public void finishActivityB(View v) {
		ActivityB.this.finish();
	}
}
