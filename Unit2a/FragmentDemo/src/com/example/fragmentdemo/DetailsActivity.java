package com.example.fragmentdemo;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * This is a secondary activity, to show what the user has selected when the
 * screen is not large enough to show both TitelsFragment and DetailsFragment 
 * in one activity.
 */

public class DetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// If the screen is now in landscape mode, we can show the
			// dialog in-line with the list so we don't need this activity.
			finish();
			return;
		}

		if (savedInstanceState == null) {
			// During initial setup, plug in the details fragment.
			DetailsFragment details = new DetailsFragment();
			details.setArguments(getIntent().getExtras());
			getFragmentManager().beginTransaction()
					.add(android.R.id.content, details).commit(); // android.R.id.content gives you the root element of a view, without having to know its actual name/type/ID. 
		}
	}
}
