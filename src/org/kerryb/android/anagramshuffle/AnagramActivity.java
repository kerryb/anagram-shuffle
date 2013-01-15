package org.kerryb.android.anagramshuffle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnagramActivity extends Activity {
	private Database db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anagram);
		TextView textView = new TextView(this);
		String anagramId = getIntent().getExtras().getString("anagramId");
		db = new Database(this);
		Anagram anagram = db.lookupAnagram(anagramId);
		textView.setText(anagram.word());
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.anagramLayout);
		layout.addView(textView);
	}
}