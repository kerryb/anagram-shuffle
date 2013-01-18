package org.kerryb.android.anagramshuffle;

import java.util.Arrays;

public class Anagram {
	private String word;

	public Anagram(String word) {
		this.word = word;
	}
	
	public String word() {
		return word;
	}
	
	public String[] letters() {
		return Arrays.copyOfRange(word.split(""), 1, word.length() + 1);
	}
}