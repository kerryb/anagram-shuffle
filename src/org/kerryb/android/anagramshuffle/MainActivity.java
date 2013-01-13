package org.kerryb.android.anagramshuffle;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	private Database db;
	private SimpleCursorAdapter anagramListViewAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new Database(this);

		String[] fromColumns = { Database.AnagramsTable.COLUMN_WORD };
		int[] toViews = { R.id.anagram_text };
		anagramListViewAdapter = new SimpleCursorAdapter(this,
				R.layout.anagram_list_item_layout, db.allAnagramsCursor(),
				fromColumns, toViews, 0);
		setListAdapter(anagramListViewAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void addAnagram(View view) {
		EditText editText = (EditText) findViewById(R.id.newAnagramInput);
		db.addAnagram(editText.getText().toString());
		anagramListViewAdapter.swapCursor(db.allAnagramsCursor());
		anagramListViewAdapter.notifyDataSetChanged();
		editText.setText("");
	}

	public void deleteAnagram(View view) {
        int position = getListView().getPositionForView((View) view.getParent());
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete this anagram?")
				.setPositiveButton("Delete", new ConfirmDeleteListener(position))
				.setNegativeButton("Cancel", new CancelDeleteListener());
		;
		builder.show();
	}

	public class CancelDeleteListener implements OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}

	public class ConfirmDeleteListener implements OnClickListener {
		private int position;

		public ConfirmDeleteListener(int position) {
			super();
			this.position = position;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
		}
	}
}
