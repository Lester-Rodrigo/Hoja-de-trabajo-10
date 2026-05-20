import java.util.HashMap;
import java.util.Map;

public class Graph {
    public int [][] matrix;
    public int[][] floydMatrix;
    public int[][] next;
    Map<String, Integer> vertexs = new HashMap<>();

    public Graph(){   
    }

    public Map<String, Integer> getVertexs() {
        return vertexs;
    }

    public void addVertex(String name){
        vertexs.put(name, vertexs.size());
        int [][] aux = matrix;
        if (matrix == null) {
                matrix = new int [1][1];
                matrix[0][0] = 0;
        }
        else {
        matrix = new int [aux.length + 1][aux.length + 1];

        for (int i = 0; i < aux.length; i++) {
            for (int j = 0; j < aux.length; j++) {
                matrix[i][j] = aux[i][j];
            }
            matrix[i][aux.length] = -1;
            matrix[aux.length][i] = -1;
            matrix[aux.length][aux.length] = 0;
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
        next = new int[vertexs][vertexs];

        for (int i = 0; i < vertexs; i++) {
            for (int j = 0; j < vertexs; j++) {
                edge_dist[i][j] = matrix[i][j];
                if (i == j) {
                    edge_dist[i][j] = 0;
                    next[i][j] = j;
                }
                else if (edge_dist[i][j] != -1) {
                next[i][j] = j;
                }
                else {
                    edge_dist[i][j] = 999999;
                    next[i][j] = j;
                }
            }
        }

        for (int i = 0; i < vertexs; i++) {
            for (int j = 0; j < vertexs; j++) {
                for (int k = 0; k < vertexs; k++) {
                    if (edge_dist[j][i] + edge_dist[i][k] < edge_dist[j][k]) {
                        edge_dist[j][k] = edge_dist[j][i] + edge_dist[i][k];
                        next[j][k] = next[j][i];
                    }
                }
            }
        }
        floydMatrix = edge_dist;
        return edge_dist;
    }

    public String getVertexName(int index){
        for (String vertex : vertexs.keySet()) {
            if (vertexs.get(vertex) == index) {
                return vertex;
            }
        }
        return null;
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

    public String printVertexs(int index){
        if (matrix != null) {
            for (String vertex : vertexs.keySet()) {

            if (vertexs.get(vertex) == index) {
                return vertex;
            }
        }
        }
        else {
            System.out.println("El grafo está vacío");
        }
        return null;
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
    
    public String graphCenter(){
        int vertexs = floydMatrix.length;
        int center = -1;
        int minEccentricity = 999999;

        for (int i = 0; i < vertexs; i++) {
            int maxDistance = 0;
            for (int j = 0; j < vertexs; j++) {
                if (floydMatrix[i][j] == 999999) {
                    maxDistance = 999999;
                    break;
                }
                if (floydMatrix[i][j] > maxDistance) {
                    maxDistance = floydMatrix[i][j];
                }
            }
            if (maxDistance < minEccentricity) {
                minEccentricity = maxDistance;
                center = i;
            }
        }
        return getVertexName(center);
    }

    public void printPath(int origin, int destination){
        if (next[origin][destination] == -1) {
            System.out.println("No existe ruta");
            return;
        }
        System.out.print(getVertexName(origin));
        while (origin != destination) {
            origin = next[origin][destination];
            System.out.print(" -> " + getVertexName(origin));
        }
        System.out.println();
    }
}