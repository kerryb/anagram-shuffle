package org.kerryb.android.anagramshuffle;

import org.kerryb.android.anagramshuffle.Database.AnagramsTable;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.SimpleCursorAdapter;

public class AnagramCursorAdapter extends SimpleCursorAdapter {
	public AnagramCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
	}

	@Override
	public void bindView(View view, final Context context, final Cursor cursor) {
		super.bindView(view, context, cursor);
		final String id = cursor.getString(cursor
				.getColumnIndex(AnagramsTable._ID));

		View button = view.findViewById(R.id.deleteButton);
		button.setOnClickListener(new DeleteButtonListener(context, id));
		View textView = view.findViewById(R.id.anagram_text);
		textView.setOnClickListener(new OpenAnagramListener(context, id));
	}
}