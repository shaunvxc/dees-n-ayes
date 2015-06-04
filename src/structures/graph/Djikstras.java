package structures.graph;

import java.util.PriorityQueue;

public class Djikstras {
	
	/**
	 * Djikstras Algo -- try to do a MinHeap implementation before the google interview!
	 * @param graph
	 * @param src
	 */
	public static void djikstra(int[][] graph,  int src) {
		/* output array */
		int[] dist = new int[graph.length];
		boolean[] visited = new boolean[graph.length];

		for (int i = 0; i < graph.length; i++) {
			dist[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		dist[src] = 0;

		for (int i = 0; i < graph.length - 1; i++) {

			// Pick the minimum distance vertex from the set of vertices not
			// yet processed. u is always equal to src in first iteration.
			int u = findMinDistanceVertex(dist, visited);
			// Mark the picked vertex as processed
			visited[u] = true;
			
			for(int j = 0; j < graph.length; j++) {
				
				// Update dist[v] only if is not in sptSet, there is an edge from 
		         // u to v, and total weight of path from src to  v through u is 
		         // smaller than current value of dist[v]
				if(!visited[j] && graph[u][j] != 0
						&& dist[u] != Integer.MAX_VALUE
						&& dist[u] + graph[u][j] < dist[j]) {
					dist[j] = dist[u] + graph[u][j];
				}
			}
		}
		
		printSolution(dist, graph.length);
	}
	
	public static void djikstraWPQueue(int[][] graph,  int src) {
		
		int[] dist = new int[graph.length];
		boolean[] visited = new boolean[graph.length];
		
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		
		for (int i = 0; i < graph.length; i++) {
		
				dist[i] = Integer.MAX_VALUE;
				visited[i] = false;
			
		}
		dist[src] = 0;
		queue.add(new Vertex(src, dist[src]));

		while(!queue.isEmpty()) {
			Vertex v = queue.remove();
			for(int i = 0; i < graph.length; i++) {
				if(!visited[i] && graph[v.name][i] != 0) {
					int alt = dist[v.name] + graph[v.name][i];
					if(alt < dist[i]) {
						dist[i] = alt;	
					}
					queue.add(new Vertex(i, dist[i]));
				}
			}
			visited[v.name ] =true;
		}

		printSolution(dist, graph.length);

	}
	
	private static void printSolution(int dist[], int n)
	{
	   System.out.println("Vertex   Distance from Source\n");
	   for (int i = 0; i < n; i++) {
	      System.out.format("%d \t\t %d\n", i, dist[i]);
	   }
	   System.out.println();
	}

	private static int findMinDistanceVertex(int[] distance, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < distance.length; i++) {
			if (!visited[i] && distance[i] <= min) {
				minIndex = i;
				min = distance[i];
			}
		}

		return minIndex;
	}

	public static void main(String[] args) {
		int[][] graph = new int[][]{ 
			{0, 4, 0, 0, 0, 0, 0, 8, 0},
            {4, 0, 8, 0, 0, 0, 0, 11, 0},
            {0, 8, 0, 7, 0, 4, 0, 0, 2},
            {0, 0, 7, 0, 9, 14, 0, 0, 0},
            {0, 0, 0, 9, 0, 10, 0, 0, 0},
            {0, 0, 4, 0, 10, 0, 2, 0, 0},
            {0, 0, 0, 14, 0, 2, 0, 1, 6},
            {8, 11, 0, 0, 0, 0, 1, 0, 7},
            {0, 0, 2, 0, 0, 0, 6, 7, 0}
		};
		
		int[][] graph2 = new int[][]{ 
			{  0 ,  40 ,  15  ,0,  0,  0},
            { 40 ,   0 ,  20  , 10  , 25 ,   6},
            { 15 ,  20  ,  0 , 100 , 0,  0},
            { 0  , 10 , 100 ,   0  ,0,  0},
            { 0,   25 , 0 , 0  ,  0  ,  8},
            { 0  ,  6 , 0  ,0  ,  8  ,  0},
		};
		
		int[][] graph3 = new int[][]{ 
				{ 0 ,9, 6, 5, 3 },
	            { 0 ,0, 0, 0 ,0},
	            {0, 2, 0 ,4, 0},
	            { 0 ,0, 0, 0 ,0},
	            { 0 ,0, 0, 0 ,0},
			};
		
		long startTime = System.currentTimeMillis();
		djikstra(graph, 0);
		System.out.println("Total time: " +  (System.currentTimeMillis() - startTime));
		
		startTime = System.currentTimeMillis();
		djikstraWPQueue(graph, 0);
		System.out.println("Total time: " +  (System.currentTimeMillis() - startTime));

		startTime = System.currentTimeMillis();
		djikstra(graph2, 0);
		System.out.println("Total time: " +  (System.currentTimeMillis() - startTime));
		
		startTime = System.currentTimeMillis();
		djikstraWPQueue(graph2, 0);
		System.out.println("Total time: " +  (System.currentTimeMillis() - startTime));
		
		startTime = System.currentTimeMillis();
		djikstra(graph3, 0);
		System.out.println("Total time: " +  (System.currentTimeMillis() - startTime));
		
		startTime = System.currentTimeMillis();
		djikstraWPQueue(graph3, 0);
		System.out.println("Total time: " +  (System.currentTimeMillis() - startTime));


	}
	
}
