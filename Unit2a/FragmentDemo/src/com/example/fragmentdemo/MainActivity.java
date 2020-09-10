package com.example.fragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Demonstration of using fragments to implement different activity layouts.
 * This sample provides a different layout (and activity flow) when run in
 * landscape instead of in portrait.
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i("FragementDemo", "MainActivity:onCreate runs ... ");

		setContentView(R.layout.fragment_layout);
	}
}