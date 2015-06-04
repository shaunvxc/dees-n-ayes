package problems;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import cs327_old.TextIO;

/**
 * DPKnap implements the bottom-up dynamic programming algorithm for the 
 * Knapsack Problem.  The program reads in knapsack data from a file, and then
 * displays both the optimal knapsack value, along with its items, to the user.
 * @author Shaun Viguerie
 * @requiredfiles: TextIO.java
 * @date March 13, 2012
 */
public class DPKnapOld {

    /* Array to contain values of each item */
    public static int[] values;
    /* Array to contain weights of each item */
    public static int[] weights;
    /* Int to store the total capacity of the knapsack */
    public static int capacity;
    /* global variable that will hold the number of items in the problem */
    public static int numItems;
    
    public static void main(String[] args) throws FileNotFoundException {
	System.out.print("Please enter the name of the file to use: ");
	String filename = TextIO.getlnWord();
	Scanner scanner = new Scanner(new File(filename));
	initializeLists(scanner);
	bottomUpKnapsack();

    }

    /**
     * Implements the bottom-up dynamic-programming algorithm to solve the 
     * knapsack problem.  Displays the results to the user. 
     * @return void
     */
    public static void bottomUpKnapsack(){
	int[][] table = new int[numItems + 1][capacity + 1];

	/* loops through to initialize top row to all 0's */
	for(int i = 0; i <= capacity; i++){
	    table[0][i] = 0;
	}
	/* loops through to initialize first column to all 0's */
	for(int i = 0; i <= numItems; i++){
	    table[i][0] = 0;
	}
	
	/* 
	 * Nested loops that perform the bulk of the bottom up dynamic
	 * programming algorithm to fill up the table
	 */
	for(int i = 1; i <= numItems; i++){
	    for(int j = 1; j <= capacity; j++){
		if(weights[i] > j){
		    table[i][j] = table[i-1][j]; 
		}
		else{
		    int value1 = table[i-1][j];
		    int value2 = values[i] + table[i-1][j - weights[i]];
		    table[i][j] = Math.max(value1, value2);
		}
	    }
	}
	
	ArrayList<Integer> bundle = assembleBundle(table, numItems, capacity);
	
	/* report solution to the user */
	System.out.println("maximum valued knapsack: " + table[numItems][capacity]);
	System.out.print("items in this bundle: ");
	for(Integer i : bundle){
	    System.out.print(i + " ");
	}
	System.out.println();

    }
    
    /**
     * assembleBundle() performs a trace of the dynamic programming algorithm, back 
     * to front, to determine the items contained within the optimal bundle for
     * the knapsack. 
     * @param int[][] table table filled from the bottom-up by the D-P algorithm.
     * @param int number of items [and thus the row index of the optimal value]
     * @param int capacity [and thus the column index of the optimal value]
     * @return ArrayList<Integer> ArrayList containing the items in the optimal
     * bundle. 
     */
    public static ArrayList<Integer> assembleBundle(int[][] table, int n, int w){
	ArrayList<Integer> bundle = new ArrayList<Integer>();
	int currentRow = n; // start tracing @ max row
	int currentCol = w; // start tracing @ max col
	int currentCapacity = w; 
	int maxValue = table[n][w];
	while(currentRow > 0){

	    // if the value is greater than the value directly below it, then 
	    // we know it fits the vi + T(i-1, j- wi) condition of the D-P alg
	    // and thus is in the knapsack.
	    if(table[currentRow][currentCol] > table[currentRow - 1][currentCol]){
		bundle.add(currentRow);
		/* jump to column associated with the capacity remaining */
		currentCol -= weights[currentRow];
		currentRow--;
	    }
	    else{
		currentRow--;
	    }    
	}
	return bundle;
    }

    /**
     * Method initializes the global variables/datastructures with the data 
     * parsed from the specified data file.
     * @param Scanner scanner scanner reading from the specified data file.
     */
    public static void initializeLists(Scanner scanner){
	capacity = scanner.nextInt();
	numItems = scanner.nextInt();
	values = new int[numItems + 1];
	weights = new int[numItems + 1];
	values[0] = 0;
	weights[0] = 0;
	scanner.nextLine(); // to get to the next line
	String line;
	String[] lineArray;
	for(int i = 1; i <= numItems; i++){
	    line = scanner.nextLine();
	    lineArray = line.split(" ");
	    weights[i] = Integer.parseInt(lineArray[0]);
	    values[i] = Integer.parseInt(lineArray[1]);
	}

    }

}