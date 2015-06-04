package structures.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import cs327_old.TextIO;

public class GraphAlgos {

	public static void bfs(Graph g, int start){
		System.out.println("BFS:");
		g.markVisited(start);
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		
		while(!queue.isEmpty()) {
			Integer currentVertex = queue.poll();
			System.out.println("visited: " + currentVertex);
			for(Integer i : g.getNeighboringVertices(currentVertex)) {
				if(!g.getVisited(i)) {
					g.markVisited(i);
					queue.add(i);
				}
			}
		}
	}
	
	public static void dfs(Graph g, int start) {
	
		System.out.println("DFS:");
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(start);
		while(!stack.isEmpty()) {
			Integer vertex = stack.pop();
			if(!g.getVisited(vertex)) {
				System.out.println("visited: " + vertex);
				g.markVisited(vertex	);
				for(Integer i : g.getNeighboringVertices(vertex)) {
					if(!g.getVisited(i)) {
						stack.push(i);
						}
				}
			}
		}
	}
	
	public static void dfsRec(Graph g, int v){
		g.markVisited(v);
		System.out.println("Visited: " + v);
		for(int i : g.getNeighboringVertices( v)) {
			if(!g.getVisited(i)) {
				dfsRec(g, i	);
			}
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		
		System.out.println("Enter file name of graph you wish to use: ");
		String filename = TextIO.getWord();
		Scanner scanner = new Scanner(new File("data/" + filename));
		
		Graph graph = new Graph(scanner);
		bfs(graph, 0);
		graph.reset();
		dfs(graph, 0);
		graph.reset();
		System.out.println("DFS Recursive");
		dfsRec(graph, 0);
	}
}
