import java.util.HashMap;
import java.util.Map;

public class Graph {
    public int [][] matrix;
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
    }

    public void addEdge(int vertex1, int vertex2, int weight){
        if (matrix[vertex1][vertex2] == -1) {
            matrix[vertex1][vertex2] = weight;
        }
        else{
            System.out.println("Arista ya existe");
        }
    }

    public void printGraph(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}