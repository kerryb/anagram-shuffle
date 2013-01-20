package org.kerryb.android.anagramshuffle;

import java.util.ArrayList;
import java.util.List;

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

	public class LettersTable implements BaseColumns {
		static final String TABLE_NAME = "letters";
		static final String COLUMN_ANAGRAM_ID = "anagram_id";
		static final String COLUMN_LETTER = "letter";
		static final String COLUMN_X_POSITION = "x";
		static final String COLUMN_Y_POSITION = "y";
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
		db.execSQL(String
				.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER)",
						LettersTable.TABLE_NAME, LettersTable._ID,
						LettersTable.COLUMN_ANAGRAM_ID,
						LettersTable.COLUMN_LETTER,
						LettersTable.COLUMN_X_POSITION,
						LettersTable.COLUMN_Y_POSITION));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void addAnagram(String word) {
		ContentValues anagramValues = new ContentValues();
		anagramValues.put(Database.AnagramsTable.COLUMN_WORD, word);
		long anagramId = database.insert(Database.AnagramsTable.TABLE_NAME,
				null, anagramValues);
		for (int i = 0; i < word.length(); i++) {
			String letter = Character.toString(word.charAt(i));
			insertLetter(anagramId, letter, (i % 4) * 100, (i / 4) * 100);
		}
	}

	private void insertLetter(long anagramId, String letter, int x, int y) {
		ContentValues letterValues = new ContentValues();
		letterValues.put(Database.LettersTable.COLUMN_ANAGRAM_ID, anagramId);
		letterValues.put(Database.LettersTable.COLUMN_LETTER, letter);
		letterValues.put(Database.LettersTable.COLUMN_X_POSITION, x);
		letterValues.put(Database.LettersTable.COLUMN_Y_POSITION, y);
		database.insert(Database.LettersTable.TABLE_NAME, null, letterValues);
	}

	public Cursor allAnagramsCursor() {
		String[] projection = { AnagramsTable._ID, AnagramsTable.COLUMN_WORD };
		String sortOrder = AnagramsTable._ID;
		return database.query(AnagramsTable.TABLE_NAME, projection, null, null,
				null, null, sortOrder);
	}

	public List<Letter> anagramLetters(String anagramId) {
		ArrayList<Letter> letters = new ArrayList<Letter>();
		String[] projection = { LettersTable._ID, LettersTable.COLUMN_LETTER,
				LettersTable.COLUMN_X_POSITION, LettersTable.COLUMN_Y_POSITION };
		String sortOrder = LettersTable._ID;
		String selection = "anagram_id = ?";
		String[] args = { anagramId };
		Cursor cursor = database.query(LettersTable.TABLE_NAME, projection,
				selection, args, null, null, sortOrder);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex(LettersTable._ID));
			String letter = cursor.getString(cursor
					.getColumnIndex(LettersTable.COLUMN_LETTER));
			int x = cursor.getInt(cursor
					.getColumnIndex(LettersTable.COLUMN_X_POSITION));
			int y = cursor.getInt(cursor
					.getColumnIndex(LettersTable.COLUMN_Y_POSITION));
			letters.add(new Letter(id, letter, x, y));
		}
		return letters;
	}

	public void deleteAnagram(String id) {
		String[] whereArgs = { id };
		database.delete(AnagramsTable.TABLE_NAME,
				String.format("%s = ?", AnagramsTable._ID), whereArgs);
		database.delete(LettersTable.TABLE_NAME,
				String.format("%s = ?", LettersTable.COLUMN_ANAGRAM_ID),
				whereArgs);
	}

	public void saveLetters(List<Letter> letters) {
		for (Letter letter : letters) {
			ContentValues letterValues = new ContentValues();
			letterValues.put(Database.LettersTable.COLUMN_X_POSITION,
					letter.x());
			letterValues.put(Database.LettersTable.COLUMN_Y_POSITION,
					letter.y());
			String[] selection = { Integer.toString(letter.id()) };
			database.update(Database.LettersTable.TABLE_NAME, letterValues,
					"_id = ?", selection);
		}
	}
}
