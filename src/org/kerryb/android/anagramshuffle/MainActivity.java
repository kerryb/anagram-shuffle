package org.kerryb.android.anagramshuffle;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {

	private Database db;
	private SimpleCursorAdapter anagramListViewAdapter;
	private ListView anagramListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new Database(this);

		String[] fromColumns = { Database.AnagramsTable.COLUMN_WORD };
		int[] toViews = { android.R.id.text1 };
		anagramListViewAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, db.allAnagramsCursor(),
				fromColumns, toViews, 0);
		anagramListView = (ListView) findViewById(R.id.anagramListView);
		anagramListView.setAdapter(anagramListViewAdapter);
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
}
