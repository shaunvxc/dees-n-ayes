package problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cs327_old.TextIO;

/**
 * DPKnap implements the bottom-up dynamic programming algorithm for the
 * Knapsack Problem. The program reads in knapsack data from a file, and then
 * displays both the optimal knapsack value, along with its items, to the user.
 * 
 * @author Shaun Viguerie
 * @requiredfiles: TextIO.java
 * @date March 13, 2012
 */
public class DPKnap2Old {

	/* Array to contain values of each item */
	public static int[] values;
	/* Array to contain weights of each item */
	public static int[] weights;
	/* Int to store the total capacity of the knapsack */
	public static int capacity;
	/* global variable that will hold the number of items in the problem */
	public static int numItems;

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Enter the name of the file you want to use: ");
		String filename = TextIO.getlnWord();
		Scanner scanner = new Scanner(new File(filename));
		initializeLists(scanner);
		findMaxValue();
	}

	/**
	 * Implements the bottom-up dynamic-programming algorithm to solve the
	 * knapsack problem. Displays the results to the user.
	 * 
	 * @return void
	 */
	public static void findMaxValue() {
		int[] maxBundles = new int[capacity + 1];
		int[] itemsUsed = new int[capacity + 1];
		int[] indicesOthers = new int[capacity + 1];
		maxBundles[0] = 0;
		int value;
		for (int i = 1; i <= capacity; i++) {
			int maxPerWeight = 0;
			for (int j = 1; j <= numItems; j++) {
				if (weights[j] <= i) {
					value = values[j] + maxBundles[i - weights[j]];
					if (value > maxPerWeight) {
						maxBundles[i] = value;
						itemsUsed[i] = j;
						indicesOthers[i] = i - weights[j];

						maxPerWeight = value;
					}
				}
			}
		}
		System.out.print("Max Bundles: ");
		for (int i = 0; i < maxBundles.length; i++) {
			System.out.print(maxBundles[i] + " ");
		}
		System.out.println();
		System.out.println("knapsack contains: ");
		int tracer = capacity;
		while (tracer > 0) {
			System.out.println("Item " + itemsUsed[tracer]);
			tracer = indicesOthers[tracer];
		}
	}

	/**
     */
	public static void initializeLists(Scanner scanner) {
		capacity = scanner.nextInt();
		numItems = scanner.nextInt();
		values = new int[numItems + 1];
		weights = new int[numItems + 1];
		values[0] = 0;
		weights[0] = 0;
		scanner.nextLine(); // to get to the next line
		String line;
		String[] lineArray;
		for (int i = 1; i <= numItems; i++) {
			line = scanner.nextLine();
			lineArray = line.split(" ");
			weights[i] = Integer.parseInt(lineArray[0]);
			values[i] = Integer.parseInt(lineArray[1]);
		}

	}

}