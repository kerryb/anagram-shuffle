package org.kerryb.android.anagramshuffle;

public class Letter {
	private int id;
	private String letter;
	private int x;
	private int y;

	public Letter(int id, String letter, int x, int y) {
		this.id = id;
		this.letter = letter;
		this.x = x;
		this.y = y;
	}

	public int x() {
		return x;
	}

	public int y() {
		return y;
	}

	public String letter() {
		return letter;
	}
}
