package problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// possibly add a solve interface or abstract base class to handle I/O
public class KnapsackSolver {

	private int[] values;
	private int[] weights;

	/* Int to store the total capacity of the knapsack */
	private int capacity;
	/* global variable that will hold the number of items in the problem */
	private int numItems;

	public KnapsackSolver(String filename) throws FileNotFoundException {
		// should be checking if file exists

		Scanner scanner = new Scanner(new File(filename));
		init(scanner);
	}
	
	public void solveProblem() {
		
		int[][] table = new int[numItems + 1][capacity + 1];
	
		// initially 0 out top row
		for(int i = 0 ; i <= capacity; i++) {
			table[0][i] = 0; // if you are holding no items...?
		}	
		
		for(int i = 0 ; i <= numItems; i++) {
			table[i][0] = 0; // first columns should be all 0's
		}
		
		for(int i= 1; i <= numItems; i++) {
			for(int j = 1; j <= capacity; j++ ) {
				if(weights[i] > j) {
					table[i][j] = table[i - 1][j];
				}
				else {
					int val1 = table[i - 1][j];
					int val2 = values[i] + table[i-1] [j - weights[i]];
					table[i][j] = Math.max(val1, val2);
				}
			}
		}
		
		List<Integer>	bundle = getBundle(table);
		/* report solution to the user */
		System.out.println("maximum valued knapsack: " + table[numItems][capacity]);
		System.out.print("items in this bundle: ");
		
		for(Integer i : bundle){
		    System.out.print(i + " ");
		}
		
		System.out.println();
	}
	
	private List<Integer> getBundle(int[][] table) {
		List<Integer> bundle  = new ArrayList<Integer>();
		int currentRow = numItems;
		int currentCol = capacity;
		while(currentRow > 0) {
			if(table[currentRow][currentCol] > table[currentRow-1][currentCol]) {
				bundle.add(currentRow);
				currentCol -= weights[currentRow]; // jump to column associateed with the capacity remaining
				currentRow--;
			}
			else {
				currentRow--;
			}
		}
		
		return bundle;
	}
	
	private void init(Scanner scanner) {
		
		capacity = scanner.nextInt();
		numItems = scanner.nextInt();
		values = new int[numItems + 1];
		weights = new int[numItems + 1];
	
		values[0] = 0;
		weights[0] = 0;
		
		scanner.nextLine(); // to get to the next line
		String[] lineArray;
		for(int i = 1; i <= numItems; i++){
		    lineArray = scanner.nextLine().split(" ");
		    weights[i] = Integer.parseInt(lineArray[0]);
		    values[i] = Integer.parseInt(lineArray[1]);
		}
	}

	
	public static void main(String[] args) throws FileNotFoundException {
		(new KnapsackSolver("data/knapsack.txt")).solveProblem();
	}
}
