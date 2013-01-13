package org.kerryb.android.anagramshuffle;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
		anagramListViewAdapter = new AnagramCursorAdapter(this,
				R.layout.anagram_list_item_layout, db.allAnagramsCursor(),
				fromColumns, toViews, 0);
		setListAdapter(anagramListViewAdapter);
	}

	public void addAnagram(View view) {
		EditText editText = (EditText) findViewById(R.id.newAnagramInput);
		db.addAnagram(editText.getText().toString());
		anagramListViewAdapter.swapCursor(db.allAnagramsCursor());
		anagramListViewAdapter.notifyDataSetChanged();
		editText.setText("");
	}

	public class ConfirmDeleteListener implements OnClickListener {
		private String id;

		public ConfirmDeleteListener(String id) {
			super();
			this.id = id;
		}

		@Override
		public void onClick(DialogInterface dialog, int which) {
			db.deleteAnagram(id);
			anagramListViewAdapter.swapCursor(db.allAnagramsCursor());
			anagramListViewAdapter.notifyDataSetChanged();
		}
	}
}
