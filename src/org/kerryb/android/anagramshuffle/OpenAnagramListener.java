package org.kerryb.android.anagramshuffle;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class OpenAnagramListener implements OnClickListener {
	private Context context;
	private String anagramId;

	public OpenAnagramListener(Context context, String anagramId) {
		this.context = context;
		this.anagramId = anagramId;
	}

	@Override
	public void onClick(View view) {
		Intent intent = new Intent(context, AnagramActivity.class);
		intent.putExtra("anagramId", anagramId);
		context.startActivity(intent);
	}
}
