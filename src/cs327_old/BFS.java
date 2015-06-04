package cs327_old;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BFS {
	/* ArrayList to contain the adjacency list specification of the graph */
	public static ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	/*
	 * Global variable to store the value of the first vertex specified by the
	 * user
	 */
	public static int vertexOne;
	/*
	 * Global variable to store the value of the second vertex specified by the
	 * user
	 */
	public static int vertexTwo;
	/*
	 * Global variable to keep track of the value of the shortest u-v path seen
	 * so far
	 */
	public static int shortestPathLen;
	/* Global variable to store the number of shortest u-v paths seen so far */
	public static int numShortestPaths;

	public static void main(String[] args) throws FileNotFoundException {
		System.out.print("Enter the name of the file you wish to use: ");
		String filename = TextIO.getWord();
		Scanner scanner = new Scanner(new File(filename));
		initializeLists(scanner);
		System.out.print("Enter the number of the first vertex: ");
		vertexOne = TextIO.getlnInt();
		if (vertexOne >= adjList.size() || vertexTwo < 0) {
			System.out.println("error: specified vertex does not exist, exiting. ");
			System.exit(1);
		}
		System.out.print("Enter the number of the second vertex: ");
		vertexTwo = TextIO.getlnInt();
		if (vertexTwo >= adjList.size() || vertexTwo < 0) {
			System.out.println("error: specified vertex does not exist, exiting. ");
		} else if (vertexTwo == vertexOne) {
			System.out.println("error: entered the same vertex twice, exiting. ");
		} else {
			numShortestPaths = 0;
			shortestPathLen = 1000;
			bfs(vertexOne);
			System.out.println("The length of the shortest path(s) from vertex " + vertexOne + " to vertex " + vertexTwo + " is " + shortestPathLen);
			System.out.println("Number of shortest paths from vertex " + vertexOne + " to vertex " + vertexTwo + ": " + numShortestPaths);
		}
	}

	/**
	 * Implementation of the BFS algorithm that counts the number of shortest
	 * paths between two specified vertices (specified vertex values stored
	 * globally.
	 * 
	 * @param int i the start index of the search
	 */
	public static void bfs(int i) {
		ArrayList<Integer> list = adjList.get(i);
		ArrayList<Integer> neighborList;
		list.set(0, 0);
		adjList.set(i, list);
		QueueOfStrings queue = new QueueOfStrings();
		queue.nq(i + "");
		int size;
		while (!queue.isEmpty()) {
			int currentVertex = Integer.parseInt(queue.dq());
			list = adjList.get(currentVertex);
			size = list.size();
			for (int k = 1; k < size; k++) {
				int neighbor = list.get(k);
				neighborList = adjList.get(neighbor);
				// vertex has not been visited yet...
				if (neighborList.get(0) == -1) {
					// if the neighbor is the vertex of interest..
					if (neighbor == vertexTwo) {
						int pathLength = list.get(0) + 1;
						// if we have found a new shortest path...
						if (pathLength < shortestPathLen) {
							shortestPathLen = pathLength;
							// set the path counter to 1.
							numShortestPaths = 1;
						}
						// if we have found another u-v path that is
						// the same length as the shortest uv path..
						else if (pathLength == shortestPathLen) {
							// then increment the shortest path counter
							numShortestPaths++;
						}
					} else {
						// label the vertex w a label that represents its
						// distance from the start vertex
						neighborList.set(0, list.get(0) + 1);
						adjList.set(neighbor, neighborList);
						queue.nq(neighbor + ""); // add it to the queue.
					}
				}
			}
		}
	}

	/**
	 * Method that initializes global data structures and variables with data
	 * from a specified file.
	 * 
	 * @param Scanner
	 *            scanner scanner reading data from the specified file.
	 */
	public static void initializeLists(Scanner scanner) {
	
		int numVertices = scanner.nextInt();
		String line = scanner.nextLine();
		String[] lineArray;
		
		for (int i = 0; i < numVertices; i++) {
			line = scanner.nextLine();
			lineArray = line.split(" ");
			int idx = Integer.parseInt(lineArray[0]);
			ArrayList<Integer> tempList = new ArrayList<Integer>();
			tempList.add(-1); // all vertices are intially unmarked
			for (int j = 1; j < lineArray.length; j++) {
				tempList.add(Integer.parseInt(lineArray[j]));
			}
			adjList.add(tempList);
		}

	}
}
