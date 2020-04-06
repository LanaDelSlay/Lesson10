package exercises;

import java.util.List;

import examples.FileHelper;

public class Palindrome {

	public List<String> loadWords() {
		return FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	}
	
	public boolean isPalin(String word) {
		StringBuilder wordTest = new StringBuilder(word);
		wordTest = wordTest.reverse();
		if(wordTest.toString().equalsIgnoreCase(word)) {
			return true;
		} else return false;
	}

	public boolean exists(String word) {
		
		if (this.loadWords().contains(word)) {
			return true;
		} else return false;
		
	}
}
