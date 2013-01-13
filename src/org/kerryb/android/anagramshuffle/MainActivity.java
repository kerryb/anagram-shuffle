package org.kerryb.android.anagramshuffle;

import android.app.ListActivity;
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

	public void deleteAnagram(String id) {
		db.deleteAnagram(id);
		anagramListViewAdapter.swapCursor(db.allAnagramsCursor());
		anagramListViewAdapter.notifyDataSetChanged();
	}
}
