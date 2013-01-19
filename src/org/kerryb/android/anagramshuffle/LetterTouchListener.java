package org.kerryb.android.anagramshuffle;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class LetterTouchListener implements OnTouchListener {
	private View letterView;

	public LetterTouchListener(View letterView) {
		super();
		this.letterView = letterView;
	}

	@Override
	public boolean onTouch(View view, MotionEvent event) {
		LayoutParams layoutParams = (RelativeLayout.LayoutParams) letterView
				.getLayoutParams();
		if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
			int x_coord = (int) (view.getLeft() + event.getX());
			int y_coord = (int) (view.getTop() + event.getY());
			layoutParams.leftMargin = x_coord;
			layoutParams.topMargin = y_coord;
			letterView.setLayoutParams(layoutParams);
		}
		return true;
	}
}
