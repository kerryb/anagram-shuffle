package org.kerryb.android.anagramshuffle;

import org.kerryb.android.anagramshuffle.Database.AnagramsTable;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
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
		button.setOnClickListener(new DeleteButtonListener(id, context));
	}

	private final class DeleteButtonListener implements OnClickListener {
		private final String id;
		private final Context context;

		private DeleteButtonListener(String id, Context context) {
			this.id = id;
			this.context = context;
		}

		public void onClick(View arg0) {
			AlertDialog.Builder builder = new AlertDialog.Builder(context);
			builder.setMessage("Are you sure you want to delete this anagram?")
					.setPositiveButton(
							"Delete",
							((MainActivity) context).new ConfirmDeleteListener(
									id)).setNegativeButton("Cancel", null);
			;
			builder.show();

		}
	}
}