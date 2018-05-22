package progPKJ;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Dijikstra {

	private static final int noparent = -1;

	private static void dijkstra(int[][] adjacencyMatrix, int source) {
		int nVertices = adjacencyMatrix[0].length;
		int[] shortestDistances = new int[nVertices];
		boolean[] added = new boolean[nVertices];
		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			shortestDistances[vertexIndex] = Integer.MAX_VALUE;
			added[vertexIndex] = false;
		}
		shortestDistances[source] = 0;
		int[] parents = new int[nVertices];
		parents[source] = noparent;
		for (int i = 1; i < nVertices; i++) {
			int neighbourVertex = -1;
			int shortestDistance = Integer.MAX_VALUE;
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
					neighbourVertex = vertexIndex;
					shortestDistance = shortestDistances[vertexIndex];
				}
			}
			added[neighbourVertex] = true;
			for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
				int edge = adjacencyMatrix[neighbourVertex][vertexIndex];
				if (edge > 0 && ((shortestDistance + edge) < shortestDistances[vertexIndex])) {
					parents[vertexIndex] = neighbourVertex;
					shortestDistances[vertexIndex] = shortestDistance + edge;
				}
			}
		}

		printSolution(source, shortestDistances, parents);
	}

	private static void printSolution(int startVertex, int[] distances, int[] parents) {
		int nVertices = distances.length;

		for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) {
			if (vertexIndex != startVertex) {
				System.out.print("\n\nDistance from node " + startVertex + " to node " + vertexIndex + " = "
						+ distances[vertexIndex] + "\nPath = ");
				printPath(vertexIndex, parents);
			}
		}
	}

	private static void printPath(int currentVertex, int[] parents) {
		if (currentVertex == noparent) {
			return;
		}
		printPath(parents[currentVertex], parents);
		System.out.print(currentVertex + " ");
	}

	public static void readDijikstraAlgoFile() throws FileNotFoundException {
		int n;
		int source;
		File file = new File("testDijikstra.txt");
		Scanner s = new Scanner(file);
		n = s.nextInt();
		int[][] adjacencyMatrix = new int[n][n];

		for (int i = 0; i < n; i++) { // Scanning the graph
			for (int j = 0; j < n; j++)
				adjacencyMatrix[i][j] = s.nextInt();
		}
		source = s.nextInt();
		s.close();
		try {
			dijkstra(adjacencyMatrix, source);
		} catch (Exception ex) {
			System.out.println("Error occured");
		}
	}
}