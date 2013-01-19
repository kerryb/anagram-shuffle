package org.kerryb.android.anagramshuffle;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class AnagramActivity extends Activity {
	private Database db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anagram);
		String anagramId = getIntent().getExtras().getString("anagramId");
		db = new Database(this);
		Anagram anagram = db.lookupAnagram(anagramId);
		RelativeLayout viewLayout = (RelativeLayout) findViewById(R.id.anagramLayout);
		String[] letters = anagram.letters();
		for (int i = 0; i < letters.length; i++) {
			final TextView textView = new TextView(this);
			textView.setText(letters[i]);
			textView.setTextSize(36);
			viewLayout.addView(textView);
			LayoutParams letterLayout = (LayoutParams) textView
					.getLayoutParams();
			letterLayout.leftMargin = (i % 4) * 100;
			letterLayout.topMargin = (i / 4) * 100;
			letterLayout.alignWithParent = true;
			textView.setLayoutParams(letterLayout);
			textView.setOnTouchListener(new LetterTouchListener(textView));
		}
	}
}