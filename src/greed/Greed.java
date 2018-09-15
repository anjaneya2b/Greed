//Anjaneya Bhardwaj
package greed;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Greed {

	public static ArrayList<Entry> players = new ArrayList<Entry>(); // each player is represented by an entry
																		
	public static int numOfTrials = 5;
	public static int numOfPlayers = 19;
	public static int numOfGuesses = 9;


	private static void parse(String lineIn) {
		String[] parsedString = lineIn.replaceAll(" ", "").split("[,]"); // remove spaces then make an array by splitting the string across commas
																		
		String name = parsedString[0];
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int x = 1; x < parsedString.length; x++)
			numbers.add(Integer.parseInt(parsedString[x]));
		Entry entry = new Entry(name, numbers);
		players.add(entry);
	}

	public static void main(String[] args) {

		FileReader dataIn = null;
		BufferedReader dataBuffer = null;

		String fileName = "input.txt";

		// this part opens the data file
		try {
			 System.out.println("Trying to open the file...");
			// Create file reader
			dataIn = new FileReader(fileName);
			dataBuffer = new BufferedReader(dataIn);
			 System.out.println("File opened.");
			 
		} catch (FileNotFoundException e) { //if no input file is found then end
			System.out.println("I couldn't find that file!");
			e.printStackTrace();
			System.exit(0);
		}

		// this part reads the data from the file
		String lineIn;
		int lineCount = 1;
		try {
			while ((lineIn = dataBuffer.readLine()) != null) {
				 System.out.println("Parsing line "+lineCount++);

				parse(lineIn);
			}
		} catch (IOException e) {
			System.out.println("I couldn't read that line!");
			e.printStackTrace();
		}


		for (int column = 0; column < players.get(0).getNumbers().size(); column++) {

			for (int player = 0; player < players.size(); player++) {

				double playerGuess = players.get(player).getNumbers().get(column);
				double occurance = 0;

				for (int row = 0; row < players.size(); row++) {

					if (players.get(row).getNumbers().get(column) == playerGuess)
						occurance++;

				}

				players.get(player).addToScore(playerGuess / occurance);

			}

		}



		for (int y = 0; y < players.size(); y++) {
			double top = 0;
			int topInd = 0;
			for (int x = y; x < players.size(); x++) {

				double lastScore = players.get(x).getScore();

				if (players.get(x).getScore() > top) {
					top = lastScore;
					topInd = x;
				}

			}

			swap(topInd, y, players);

		}

		System.out.println("Results are in!");
		System.out.println("Printing to file...");
		// this part writes the results file
		FileOutputStream fileOut;
		PrintStream printOut = null;
		try {
			fileOut = new FileOutputStream("results.txt");
			printOut = new PrintStream(fileOut);
			for (int i = 0; i < players.size(); i++) {
				// at least 6 characters for the score printout
				// System.out.printf("%-" + 6 + "s: %s%n", players.get(i).getScore(), players.get(i).getName());
				// in order to test
				printOut.printf("%-" + 6 + "s: %s%n", players.get(i).getScore(), players.get(i).getName()); 
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		//avoid memory leak
		if (printOut != null) {
			printOut.close();
		}
		try {
			if (dataIn != null) {
				dataIn.close();
			}
		} catch (IOException e) {
			System.out.println("I couldn't close the file reader!");
			e.printStackTrace();
		}

	}
	/**
	 * This method swaps elements in a list
	 * @param  x, y
	 * @param list the list
	 */
	public static void swap(double x, double y, ArrayList<Entry> list) {
		list.add(list.get((int) x));
		list.set((int) x, list.get((int) y));
		list.set((int) y, list.get(list.size() - 1));
		list.remove(list.size() - 1);
	}

}