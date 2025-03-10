import java.util.Scanner;
public class GraphColoring {
    static int[] colors;
    static int[][] graph;
    static int numVertices;
    public static boolean isSafe(int v, int color) {
        for (int i = 0; i < numVertices; i++) {
            if (graph[v][i] == 1 && colors[i] == color) {
                return false;
            }
        }
        return true;
    }

    public static boolean graphColoring(int v) {
        if (v == numVertices) {
            return true;
        }
        for (int color = 1; color <= numVertices; color++) {
            if (isSafe(v, color)) {
                colors[v] = color;
                if (graphColoring(v + 1)) {
                    return true;
                }
                colors[v] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        numVertices = scanner.nextInt();

        System.out.print("Enter number of edges: ");
        int numEdges = scanner.nextInt();

        graph = new int[numVertices][numVertices];
        colors = new int[numVertices];

        System.out.println("Enter the edges (vertex pairs): ");
        for (int i = 0; i < numEdges; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            graph[u][v] = 1;
            graph[v][u] = 1;
        }

        if (graphColoring(0)) {
            System.out.println("Solution exists with the following coloring:");
            int maxColor = 0;
            for (int i = 0; i < numVertices; i++) {
                System.out.println("Vertex " + (i + 1) + " -> Color " + colors[i]);
                if (colors[i] > maxColor) {
                    maxColor = colors[i];
                }
            }
            System.out.println("Total colors used: " + maxColor);
        } else {
            System.out.println("No solution exists.");
        }

        scanner.close();
    }
}
//outputEnter number of vertices: 4
Enter number of edges: 4
Enter the edges (vertex pairs): 
1 4
1 2
2 3
3 4
Solution exists with the following coloring:
Vertex 1 -> Color 1
Vertex 2 -> Color 2
Vertex 3 -> Color 1
Vertex 4 -> Color 2
Total colors used: 2

