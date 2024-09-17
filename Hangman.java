/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Hangman extends ConsoleProgram {

	/***********************************************************
	 * CONSTANTS *
	 ***********************************************************/

	/* The number of guesses in one game of Hangman */
	private static final int N_GUESSES = 7;
	/* The width and the height to make the karel image */
	private static final int KAREL_SIZE = 150;
	/* The y-location to display karel */
	private static final int KAREL_Y = 230;
	/* The width and the height to make the parachute image */
	private static final int PARACHUTE_WIDTH = 300;
	private static final int PARACHUTE_HEIGHT = 130;
	/* The y-location to display the parachute */
	private static final int PARACHUTE_Y = 50;
	/* The y-location to display the partially guessed string */
	private static final int PARTIALLY_GUESSED_Y = 430;
	/* The y-location to display the incorrectly guessed letters */
	private static final int INCORRECT_GUESSES_Y = 460;
	/* The fonts of both labels */
	private static final String PARTIALLY_GUESSED_FONT = "Courier-36";
	private static final String INCORRECT_GUESSES_FONT = "Courier-26";

	/***********************************************************
	 * Instance Variables *
	 ***********************************************************/

	/* An object that can produce pseudo random numbers */
	private RandomGenerator rg = new RandomGenerator();

	private GCanvas canvas = new GCanvas();

	/***********************************************************
	 * Methods *
	 ***********************************************************/

	public void run() {
		// data

		String wordToGuess = getRandomWord();
		Set<Character> lettersAttempted = new HashSet<>();
		Set<Character> lettersInWord = new HashSet<>();
		String currentGuess = "";
		int guessCounter = N_GUESSES;
		Scanner scanner = new Scanner(System.in);

		// shall we?
		// program
		System.out.println("Welcome to hangman");
		for (char c : wordToGuess.toCharArray()) {
			lettersInWord.add(c);
		}
		do {
			char charGuess;
			String lineInput;

			currentGuess = "";
			System.out.print("Your word now looks like this: ");
			for (char c : wordToGuess.toCharArray()) {
				if (lettersAttempted.contains(c)) {
					currentGuess += c;
				} else {
					currentGuess += '-';
				}
			}
			System.out.println(currentGuess);
			System.out.println("You have " + guessCounter + " guesses left");
			System.out.print("Your guess: ");
			lineInput = scanner.nextLine();
			while (!(lineInput.length() == 1 && Character.isAlphabetic(charGuess = lineInput.charAt(0)))) {
				System.out.print("Please, try again: ");
				lineInput = scanner.nextLine();
			}

			charGuess = Character.toUpperCase(charGuess);

			if (!lettersInWord.contains(charGuess)) {
				System.out.println("There are no " + charGuess + "'s in the word");
			} else if (!lettersAttempted.contains(charGuess)) {
				System.out.println("Your guess is correct");
				lettersAttempted.add(charGuess);
				guessCounter--;
			}

		} while (currentGuess != wordToGuess && guessCounter > 0);

		if (guessCounter == 0) {
			System.out.println("You lost! 😭");
		} else {
			System.out.println("You win! 😄");
		}
		System.out.println("The word was: " + wordToGuess);
	}

	/**
	 * Method: Get Random Word ------------------------- This method returns a word
	 * to use in the hangman game. It randomly selects from among 10 choices.
	 */
	private String getRandomWord() {
		int index = rg.nextInt(10);
		if (index == 0)
			return "BUOY";
		if (index == 1)
			return "COMPUTER";
		if (index == 2)
			return "CONNOISSEUR";
		if (index == 3)
			return "DEHYDRATE";
		if (index == 4)
			return "FUZZY";
		if (index == 5)
			return "HUBBUB";
		if (index == 6)
			return "KEYHOLE";
		if (index == 7)
			return "QUAGMIRE";
		if (index == 8)
			return "SLITHER";
		if (index == 9)
			return "ZIRCON";
		throw new ErrorException("getWord: Illegal index");
	}

}
