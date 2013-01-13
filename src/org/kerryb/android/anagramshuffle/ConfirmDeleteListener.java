package org.kerryb.android.anagramshuffle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class ConfirmDeleteListener implements OnClickListener {
	private final MainActivity mainActivity;
	private String id;

	public ConfirmDeleteListener(Context context, String id) {
		super();
		this.mainActivity = (MainActivity) context;
		this.id = id;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		mainActivity.deleteAnagram(id);
	}
}