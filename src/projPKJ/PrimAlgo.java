package projPKJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class PrimAlgo {
	
	private static int V = 0; // Number of vertices in the graph

	// find the vertex with minimum key value
	static int findMinKey(int key[], Boolean vertixQueue[]) {
		int min = Integer.MAX_VALUE; // Infinity value
		int minIndex = -1;

		for (int i = 0; i < V; i++) {
			if (vertixQueue[i] == false && key[i] < min) {
				min = key[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	// A utility function to print the constructed MST stored in

	static void primAlgo(int graph[][]) {
		int parent[] = new int[V]; // Array to store parents of every node -> MST
		int key[] = new int[V]; // Key values used to pick minimum weight edge in cut
		Boolean vertixQueue[] = new Boolean[V]; // To represent set of vertices not yet included in MST

		// Initialize all keys as INFINITE
		for (int i = 0; i < V; i++) {
			key[i] = Integer.MAX_VALUE;
			vertixQueue[i] = false;
		}

		// 1st vertex in MST.
		key[0] = 0; // Make key 0 so that this vertex is
		parent[0] = -1; // Cosidering first element as starting point

		// The MST will have V vertices
		for (int i = 0; i < V - 1; i++) {
			// Pick thd minimum key vertex from the set of vertices not yet included in MST
			int u = findMinKey(key, vertixQueue);

			// Add the picked vertex to the MST Set
			vertixQueue[u] = true;

			// Update key value and parent index of the adjacent vertices of the picked
			// vertex.
			for (int v = 0; v < V; v++)

				if (graph[u][v] != 0 && vertixQueue[v] == false && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}
		
		System.out.println("Edge   Weight");
		int COUNT = 0;
		for (int i = 1; i < V; i++) {
			System.out.println(parent[i] + " -> " + i + "    " + graph[i][parent[i]]);
			COUNT += graph[i][parent[i]];
		}
		System.out.println("Total weight of the MST -> "+COUNT);
	}

	public static void readPrimAlgoFile() throws FileNotFoundException {
		File file = new File("testPrim.txt");
		Scanner s = new Scanner(file);
		V = s.nextInt();
		int[][] graph = new int[V][V];

		for (int i = 0; i < V; i++) { // Scanning the graph
			for (int j = 0; j < V; j++)
				graph[i][j] = s.nextInt();
		}
		s.close();
		try {
			PrimAlgo.primAlgo(graph);
		} catch (Exception ex) {
			System.out.println("Error occured");
		}
	}
}
