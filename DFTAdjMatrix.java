import java.util.*;

class Graph {
    private int vertices;
    private int[][] adjMatrix;

    public Graph(int v) {
        vertices = v;
        adjMatrix = new int[v][v];
    }

    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
        adjMatrix[dest][src] = 1; // Assuming an undirected graph
    }

    public void DFT(int startVertex) {
        boolean[] visited = new boolean[vertices];
        System.out.print("Depth-First Traversal: ");
        DFTUtil(startVertex, visited);
        System.out.println();
    }

    private void DFTUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        
        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                DFTUtil(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 4);
        g.addEdge(3, 5);
        g.addEdge(4, 5);
        
        g.DFT(0); // Starting from vertex 0
    }
}
