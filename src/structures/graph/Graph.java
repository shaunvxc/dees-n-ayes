package structures.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Graph {
	
	
	private List<List<Integer>> adjList;
	private boolean[] visited;
	
	public Graph(Scanner scanner) {
		initGraph(scanner);
	}

	public List<Integer> getNeighboringVertices(int i) {
		return adjList.get(i);
	}
	
	public void markVisited(int i) {
		visited[i] = true;
	}
	
	public boolean getVisited(int i) {
		return visited[i];
	}
	
	public void reset() {
		for(int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
	}
	
	private void initGraph(Scanner scanner)  {
		
		int numVertices = scanner.nextInt();
		scanner.nextLine();
		adjList = new ArrayList<List<Integer>>(numVertices);
		visited = new boolean[numVertices];
		String[] neighbors;
		
		for(int i = 0; i < numVertices; i++) {
			
			neighbors = scanner.nextLine().split(" ");
			List<Integer> temp = new ArrayList<Integer>(neighbors.length - 1);
			
			for(int j= 1; j < neighbors.length; j++) {
				temp.add(Integer.parseInt(neighbors[j]));
			}
			
			adjList.add(temp);
			visited[i] = false;
		}
	}
	
	
}
