
package hangmangame;

import java.util.*;

/**
 * Class to model a hangman game.
 * @author John H
 */
public class HangmanGame {
	
	public static void main(String[] args) {
		TreeSet lttrsGuessedCorrect = new TreeSet();
		TreeSet lttrsGuessedIncorrect = new TreeSet();
		String inputP1, inputP2;
		char p2Guess;
		
		System.out.println("Player 1, please enter a secret word "
		 + "without spaces, then press enter:");
		Scanner scanner = new Scanner(System.in);
		inputP1 = scanner.nextLine();
		int guesses = 0;
		
		while (guesses != 6) {
			System.out.println("\nPlayer 2, please guess a letter:");
			inputP2 = scanner.next();
			inputP2 = inputP2.toLowerCase();
			p2Guess = inputP2.charAt(0);
			
			if (p2Guess < 97 || p2Guess > 122) {
				System.out.println("\nThat is not a letter, so you won't be"
				 + " penalized for this guess.");
				guesses--;
			}
			
			if (p2Guess > 96 && p2Guess < 123) {
				if (inputP1.contains(inputP2)) {
					lttrsGuessedCorrect.add(p2Guess);
					System.out.println("Yay! Letter " + p2Guess + " is in the "
					 + "secret word so it will be added to the correct list "
					 + "of letters!");
					guesses--;
					
					if (lttrsGuessedCorrect.getCount() == inputP1.length()) {
						System.out.println("YOU GUESSED ALL THE LETTERS AND WON "
						 + "THE GAME!!! The secret word is: ");
						System.out.println(inputP1);
						System.exit(0);
					}
				}
				else {
					lttrsGuessedIncorrect.add(p2Guess);
					System.out.println("\nSorry. That letter is not in "
					 + "the secret word.");
					switch (guesses) {
					case 0:
						System.out.println("Parts of Body Added: head."); 
						break;
					case 1:
						System.out.println("Parts of Body Added: torso, head.");
						break;
					case 2:
						System.out.println("Parts of Body Added: left arm, torso, "
						 + "head.");
						break;
					case 3:
						System.out.println("Parts of Body Added: right arm, left arm,"
						 + " torso, head.");
						break;
					case 4:
						System.out.println("Parts of Body Added: left leg, right arm,"
						 + " left arm, torso, head.");
						break;
					case 5:
						System.out.println("Parts of Body Added: right leg, left leg,"
						 + " right arm, left arm, torso, head.");
						break;
					}
				}
			}
				
			System.out.println("These are the letters you've incorrectly "
			 + "guessed:");
			lttrsGuessedIncorrect.printAll();
			//They only print in alphabetical order because the set-nature of the
			//BST doesn't record the sequence of entry, only the comparable keys.
			
			if (guesses == 5) {
				System.out.println("You lost the game. Goodbye!");
				System.exit(0);
			}
			guesses++;
		}
	}
}