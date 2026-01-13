import java.util.Scanner;

abstract class Graph {
    int vertices;

    int[][] adjacency_matrix;

    public abstract void getGraph();

    public void display() {
        if (adjacency_matrix == null) {
            System.out.println("Not Initialized..!");
            getGraph();
        }
        System.out.println("\n\t Adjacency Matrix \n------------------------------------");
        for (int i=0; i<vertices; i++) {
            for (int j=0; j<vertices; j++) {
                System.out.print(adjacency_matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------");
    }

    public abstract boolean isConnected();
}

class DirectedGraph extends Graph {

    public DirectedGraph() {

    }

    public DirectedGraph(int[][] adjacency_matrix) {
        vertices = adjacency_matrix.length;
        this.adjacency_matrix = adjacency_matrix;
    }

    @Override
    public void getGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        vertices = sc.nextInt();

        adjacency_matrix = new int[vertices][vertices];

        System.out.println("Enter adjacency matrix (0 or 1): ");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacency_matrix[i][j] = sc.nextInt();

                if (i == j) adjacency_matrix[i][j] = 0;
            }
        }
    }

    public int getIndegree(int vertex) {
        int indegree = 0;

        for(int i=0; i<vertices; i++){
            if (adjacency_matrix[i][vertex] == 1) indegree++ ;
        }
        return indegree;
    }
    
    public int getOutdegree(int vertex) {
        int outdegree = 0;

        for(int i=0; i<vertices; i++){
            if (adjacency_matrix[vertex][i] == 1) outdegree++ ;
        }
        return outdegree;
    }



    @Override
    public boolean isConnected() {
        boolean[] visited = new boolean[vertices];
        dfs(0, visited);
        
        for (boolean v : visited) {
            if (!v) return false;
        }
            
        return true;
    }

    public void dfs(int v, boolean[] visited) {
        visited[v] = true;

        for (int i=0; i<vertices; i++) {
            if(!visited[i] && adjacency_matrix[v][i] == 1) 
                dfs(i, visited);
        }
    }

    public boolean isStronglyConnected() {
        int[][] transposed_adj = new int[vertices][vertices] ;
        boolean connected = isConnected();
        for (int i=0; i<vertices; i++) {
            for (int j=0; j<vertices; j++) 
                transposed_adj[i][j] = adjacency_matrix[j][i];
        } 

        return (connected && (new DirectedGraph(transposed_adj)).isConnected()) ;
    }
}

class WeightedGraph extends Graph {

    @Override
    public void getGraph() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: ");
        vertices = sc.nextInt();

        adjacency_matrix = new int[vertices][vertices];
        System.out.println("Enter weighted adjacency matrix (0 if no edge): ");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjacency_matrix[i][j] = sc.nextInt();

                if (i == j) adjacency_matrix[i][j] = 0;
            }
        }
    }

    @Override
    public boolean isConnected() {
        boolean[] visited = new boolean[vertices];
        dfs(0, visited);
        
        for (boolean v : visited) {
            if (!v) return false;
        }
            
        return true;
    }

    public void dfs(int v, boolean[] visited) {
        visited[v] = true;

        for (int i=0; i<vertices; i++) {
            if(!visited[i] && adjacency_matrix[v][i] != 0) 
                dfs(i, visited);
        }
    }

    public void dijkstra(int src) {
        int[] dist = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int[] path = new int[vertices];


        for (int i = 0; i < vertices; i++) {
            dist[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }

        dist[src] = 0;

        for (int count = 0; count < vertices - 1; count++) {
            int u = minDistance(dist, visited);
            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && adjacency_matrix[u][v] != 0 && 
                    dist[u] != Integer.MAX_VALUE &&
                    dist[u] + adjacency_matrix[u][v] < dist[v]) {
                    dist[v] = dist[u] + adjacency_matrix[u][v];
                    path[v] = u;
                }
            }
        }

        System.out.println("Vertex\tDistance from Source\tShortest Path");
        for (int i = 0; i < vertices; i++) {
            System.out.print((i + 1) + "\t\t" + dist[i] + "\t\t");
            shortestPath(path, i);
            System.out.println();
        }
    }

    private int minDistance(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        for (int v = 0; v < vertices; v++) {
            if (!visited[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public void shortestPath(int[] path, int vertex) {
        if (vertex == -1) 
            return;
        
        shortestPath(path, path[vertex]);
        System.out.print(((path[vertex] != -1) ? "-->" : "") + vitc(vertex));
    }

    private char vitc(int index) {
        return (char)('A' + index);
    }

}

public class DemoGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ch;
        

        do { 
            System.out.print("""
                             ------------Menu---------
                             1. DirectedGraph
                             2. WeightedGraph
                             0. Exit
                             Your Choice: """);
            
            ch = sc.nextInt();

            switch(ch) {
                case 1:
                    DirectedGraph g = new DirectedGraph();
                    int ch_g;
                    do { 
                        System.out.print("""
                                        ------------ Directed Graph--------------
                                        1. Input Graph (Through Adjacency Matrix)
                                        2. Display Adjacency Matrix
                                        3. Indegree & Outdegree
                                        4. Is Connected? 
                                        5. Is Strongly Connected?
                                        6. Previous Menu
                                        Your Choice: 
                                        """);
                        
                        ch_g = sc.nextInt();

                        switch(ch_g) {
                            case 1:
                                g.getGraph();
                                break;
                            
                            case 2:
                                g.display();
                                break;
                            
                            case 3:
                                System.out.print("Enter Vertex No. (1 - " + g.vertices + ") or (0 for all): ");
                                int vertex = sc.nextInt();

                                if (vertex >= 0 && vertex <= g.vertices) {
                                    if (vertex != 0) 
                                        System.out.println("Vertex " + vertex + ": " +
                                        "\nIndegree : " + g.getIndegree(vertex - 1) +
                                        "\nOutdegree: " + g.getOutdegree(vertex - 1) 
                                        );
                                    
                                    else {
                                        for (int i=0; i<g.vertices; i++) 
                                            System.out.println("\nVertex " + (i+1) + ": " +
                                                "\nIndegree : " + g.getIndegree(i) +
                                                "\nOutdegree: " + g.getOutdegree(i) 
                                            );
                                    }
                                }
                                break;

                            case 4:
                                System.out.println((g.isConnected() ? "Graph Is Connected." : "Graph Is Not Connected." ));
                                break;
                            
                            case 5:
                                System.out.println((g.isStronglyConnected() ? "Graph Is Strongly Connected." : "Graph Is Not Strongly Connected." ));

                            case 6: 
                                break;

                            default:
                                System.out.println("Invalid Choice..!");
                        }
                    } while (ch_g != 6);
                    break;

                case 2:
                    WeightedGraph wg = new WeightedGraph();

                    do { 
                        System.out.print("""
                                        ------------ Weighted Graph--------------
                                        1. Input Graph (Through Adjacency Matrix)
                                        2. Display Adjacency Matrix
                                        3. Find Minimum Cost & Path From A Source Vertex
                                        4. Previous Menu
                                        Your Choice: 
                                        """);
                        
                        ch_g = sc.nextInt();

                        switch(ch_g) {
                            case 1:
                                wg.getGraph();
                                break;
                            
                            case 2:
                                wg.display();
                                break;

                            case 3:
                                System.out.print("Enter Source Vertex (1 - " + wg.vertices + "): ");
                                int source = sc.nextInt();
                                wg.dijkstra(source-1);
                                break;

                            case 4:
                                break;
                            
                            default:
                                System.out.println("Invalid Option");
                        }

                    } while (ch_g != 4);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;
            }

        } while (ch != 0);
    }
}