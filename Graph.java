import java.util.HashMap;
import java.util.Map;

public class Graph {
    public int [][] matrix;
    public int[][] floydMatrix;
    Map<String, Integer> vertexs = new HashMap<>();

    public Graph(){   
    }

    public void addVertex(String name){
        vertexs.put(name, vertexs.size());
        int [][] aux = matrix;
        if (matrix == null) {
                matrix = new int [1][1];
                matrix[0][0] = -1;
        }
        else {
        matrix = new int [aux.length + 1][aux.length + 1];

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux.length; j++) {
                matrix[i][j] = aux[i][j];
            }
            matrix[i][aux.length] = -1;
            matrix[aux.length][i] = -1;
            matrix[aux.length][aux.length] = -1;
            }
        }
        floyd();
    }

    public void addEdge(int vertex1, int vertex2, int weight){
        if (vertex1 >= matrix.length || vertex2 >= matrix.length) {
            System.out.println("Vertice inválido");
            return;
        }

        if (matrix[vertex1][vertex2] == -1) {
            matrix[vertex1][vertex2] = weight;
            floyd();
        }
        else{
            System.out.println("Arista ya existe");
        }
    }

    public void removeEdge(int vertex1, int vertex2){

        if (vertex1 >= matrix.length || vertex2 >= matrix.length) {
            System.out.println("Vertice inválido");
            return;
        }
        if (matrix[vertex1][vertex2] != -1) {
            matrix[vertex1][vertex2] = -1;
            floyd();
            System.out.println("Arista eliminada");
        }
        else{
            System.out.println("No existe una arista entre esos vértices");
        }
    }

    public int[][] floyd(){
        int vertexs = matrix.length;
        int[][] edge_dist = new int[vertexs][vertexs];

        for (int i = 0; i < vertexs; i++) {
            for (int j = 0; j < vertexs; j++) {
                edge_dist[i][j] = matrix[i][j];
                if (i == j) {
                    edge_dist[i][j] = 0;
                }
                if (edge_dist[i][j] == -1 && i != j) {
                    edge_dist[i][j] = 999999;
                }
            }
        }

        for (int i = 0; i < vertexs; i++) {
            for (int j = 0; j < vertexs; j++) {
                for (int k = 0; k < vertexs; k++) {
                    if (edge_dist[k][i] + edge_dist[i][j] < edge_dist[k][j]) {
                        edge_dist[k][j] = edge_dist[k][i] + edge_dist[i][j];
                    }
                }
            }
        }
        floydMatrix = edge_dist;
        return edge_dist;
    }

    public void printGraph(){
        if (matrix != null) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
        else {
            System.out.println("El grafo está vacío");
        }
    }

    public void printVertexs(){
        if (matrix != null) {
            for (String vertex : vertexs.keySet()) {
            System.out.println(vertex);
            }
        }
        else {
            System.out.println("El grafo está vacío");
        }
    }

      public void printFloyd(int[][] dist){
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length; j++) {
                if (dist[i][j] == 999999) {
                    System.out.print("INF ");
                }
                else{
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    
}