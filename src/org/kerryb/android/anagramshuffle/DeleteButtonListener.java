package org.kerryb.android.anagramshuffle;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

final class DeleteButtonListener implements OnClickListener {
	private final String anagramId;
	private final Context context;

	DeleteButtonListener(Context context, String anagramId) {
		this.context = context;
		this.anagramId = anagramId;
	}

	public void onClick(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("Are you sure you want to delete this anagram?")
				.setPositiveButton(
						"Delete",
						new ConfirmDeleteListener(context,
								anagramId)).setNegativeButton("Cancel", null);
		;
		builder.show();

	}
}