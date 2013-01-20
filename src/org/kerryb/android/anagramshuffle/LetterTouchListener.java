package org.kerryb.android.anagramshuffle;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LetterTouchListener implements OnTouchListener {
	private Letter letter;
	private View letterView;

	public LetterTouchListener(Letter letter, View letterView) {
		super();
		this.letter = letter;
		this.letterView = letterView;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		LayoutParams layoutParams = (RelativeLayout.LayoutParams) letterView
				.getLayoutParams();
		if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
			letter.setX((int) (view.getLeft() + event.getX()));
			letter.setY((int) (view.getTop() + event.getY()));
			layoutParams.leftMargin = letter.x();
			layoutParams.topMargin = letter.y();
			letterView.setLayoutParams(layoutParams);
		}
		return true;
	}
}
