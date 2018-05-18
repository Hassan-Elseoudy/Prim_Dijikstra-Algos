package primPKJ;

import java.util.Scanner;

class PrimAlgo {
	// Number of vertices in the graph
	private static int V = 0;

	// find the vertex with minimum key value
	int findMinKey(int key[], Boolean mstSet[]) {
		int min = Integer.MAX_VALUE; // Infinity value
		int minIndex = -1;

		for (int i = 0; i < V; i++) {
			if (mstSet[i] == false && key[i] < min) {
				min = key[i];
				minIndex = i;
			}
		}
		return minIndex;
	}

	// A utility function to print the constructed MST stored in

	void printTheSolNow(int parent[], int n, int graph[][]) {
		System.out.println("Edge   Weight");
		for (int i = 1; i < V; i++)
			System.out.println(parent[i] + " - " + i + "    " + graph[i][parent[i]]);
	}

	void primAlgo(int graph[][]) {
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
		parent[0] = -1; // First node is always root of MST

		// The MST will have V vertices
		for (int count = 0; count < V - 1; count++) {
			// Pick thd minimum key vertex from the set of vertices not yet included in MST
			int u = findMinKey(key, vertixQueue);

			// Add the picked vertex to the MST Set
			vertixQueue[u] = true;

			// Update key value and parent index of the adjacent vertices of the picked
			// vertex.
			for (int v = 0; v < V; v++)

				// graph[u][v] is non zero only for adjacent vertices of m
				// mstSet[v] is false for vertices !included
				// Update key if graph[u][v] is smaller than key[v]
				if (graph[u][v] != 0 && vertixQueue[v] == false && graph[u][v] < key[v]) {
					parent[v] = u;
					key[v] = graph[u][v];
				}
		}

		printTheSolNow(parent, V, graph);
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter # of nodes (Verticies)?");
		V = s.nextInt();
		int[][] graph = new int[V][V];

		for (int i = 0; i < V; i++) { // Scanning the graph
			System.out.println("Enter edges weight for vertix " + i + " from 0 -> " + (V - 1));
			for (int j = 0; j < V; j++)
				graph[i][j] = s.nextInt();
		}
		s.close();
		try {
			PrimAlgo t = new PrimAlgo();
			t.primAlgo(graph);
		} catch (Exception ex) {
			System.out.println("Error occured");
		}
	}
}
