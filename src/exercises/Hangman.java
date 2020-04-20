package exercises;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

public class Hangman extends KeyAdapter {

	Stack<String> puzzles = new Stack<String>();
	static List<String> words = FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);
	static String[] specChars = { "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";",
			"<", "=", ">", "?", "@", "\\", "[", "]", "^", "_", "`", "{", "|", "}", "~" };

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		Hangman.checkWordList();
		hangman.addPuzzles();
		hangman.createUI();
	}

	private void addPuzzles() {
		puzzles.add(getRandomWord());

	}

	public static void checkWordList() {
		for (String myWords : words) {
			for (String badChars : specChars) {
				if (myWords.contains(badChars)) {
					System.out.println("Special Char detected in list");
					System.out.println("\"" + badChars + "\" somewhere in list \n");
					throw new RuntimeException();
				}

			}
		}

	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		lives = 9;
		addPuzzles();
		livesLabel.setText("" + lives);
		puzzle = puzzles.pop();
		System.out.println("puzzle is now " + puzzle);
		createBoxes();
	}

	public void keyTyped(KeyEvent arg0) {
		String input = String.valueOf(arg0.getKeyChar());
		for (int i = 0; i <= specChars.length - 1; i++) {
			if (input.equals(specChars[i])) {
				throw new RuntimeException("You typed a special char! Congrats!!!");
			}
		}

		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());
		if (lives == 0) {
			playDeathKnell();
			loadNextPuzzle();
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.toLowerCase().charAt(i) == keyChar) {
				boxes.get(i).setText("" + keyChar);
				gotOne = true;
				if (!boxes.toString().contains("_")) {
					loadNextPuzzle();
				}
			}
		}
		if (!gotOne)
			livesLabel.setText("" + --lives);
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}

	public String getRandomWord() {
		Random rand = new Random();
		String newWord = words.get(rand.nextInt(words.size()));

		for (String badChars : specChars) {
			if (newWord.contains(badChars)) {
				System.out.println("\"" + badChars + "\" attempted to be set as puzzle");
				return getRandomWord();
			}
		}
		return newWord;
	}

	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}