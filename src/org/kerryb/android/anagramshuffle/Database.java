package org.kerryb.android.anagramshuffle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class Database extends SQLiteOpenHelper {
	private static final String DB_NAME = "anagram_shuffle.db";
	private static final int DB_VERSION = 1;
	private SQLiteDatabase database;

	static class AnagramsTable implements BaseColumns {
		static final String TABLE_NAME = "anagrams";
		static final String COLUMN_WORD = "word";
	}

	public Database(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.database = this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(String.format(
				"CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT)",
				AnagramsTable.TABLE_NAME, AnagramsTable._ID,
				AnagramsTable.COLUMN_WORD));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void addAnagram(String anagram) {
		ContentValues values = new ContentValues();
		values.put(Database.AnagramsTable.COLUMN_WORD, anagram);
		database.insert(Database.AnagramsTable.TABLE_NAME, null, values);
	}

	public Cursor allAnagramsCursor() {
		String[] projection = { AnagramsTable._ID, AnagramsTable.COLUMN_WORD };

		// How you want the results sorted in the resulting Cursor
		String sortOrder = AnagramsTable._ID;

		return database.query(AnagramsTable.TABLE_NAME, projection, null, null,
				null, null, sortOrder);
	}
}
