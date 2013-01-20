package org.kerryb.android.anagramshuffle;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class AnagramActivity extends Activity {
	private Database db;
	private String anagramId;
	private List<Letter> letters;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anagram);
		db = new Database(this);
		anagramId = getIntent().getExtras().getString("anagramId");
		letters = new ArrayList<Letter>();
		addLetters();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		db.saveLetters(letters);
	}

	private void addLetters() {
		RelativeLayout viewLayout = (RelativeLayout) findViewById(R.id.anagramLayout);
		for (Letter letter : db.anagramLetters(anagramId)) {
			letters.add(letter);
			addLetterView(viewLayout, letter);
		}
	}

	private void addLetterView(RelativeLayout viewLayout, Letter letter) {
		final TextView textView = new TextView(this);
		textView.setText(letter.letter());
		textView.setTextSize(36);
		viewLayout.addView(textView);
		LayoutParams letterLayout = (LayoutParams) textView.getLayoutParams();
		letterLayout.leftMargin = letter.x();
		letterLayout.topMargin = letter.y();
		letterLayout.alignWithParent = true;
		textView.setLayoutParams(letterLayout);
		textView.setOnTouchListener(new LetterTouchListener(letter, textView));
	}
}