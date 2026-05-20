import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);

        try {
            BufferedReader br =new BufferedReader(new FileReader("guatemala.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String city1 = parts[0];
                String city2 = parts[1];
                int distance = Integer.parseInt(parts[2]);
                if (!graph.vertexs.containsKey(city1)) {
                    graph.addVertex(city1);
                }
                if (!graph.vertexs.containsKey(city2)) {
                    graph.addVertex(city2);
                }
                int v1 = graph.vertexs.get(city1);
                int v2 = graph.vertexs.get(city2);
                graph.addEdge(v1, v2, distance);
            }
            br.close();
        }
        catch (IOException e) {
            System.out.println("Error leyendo archivo");
        }
        int options;
        do {
            do {
                int distance = 0;
                System.out.println("------------Programa para encontrar la ruta más corta entre dos ciudades en Guatemala------------");
                System.out.println("1. Encontrar ruta más corta");
                System.out.println("2. Ciudad centrica");
                System.out.println("3. Modificar rutas entre ciudades");
                System.out.println("4. Mostrar matrices");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opción del 1 al 5: ");
                options = scanner.nextInt();
                switch (options) {
                    case 1:
                        System.out.println("-----------------------------------------------");
                        for (int i = 0; i < graph.vertexs.size(); i++) {
                            String city = graph.printVertexs(i);
                            System.out.println(i + ": " + city);
                        }
                        System.out.print("Ingrese el número de la ciudad de origen: ");
                        int origin = scanner.nextInt();
                        System.out.print("Ingrese el número de la ciudad de destino: ");
                        int destination = scanner.nextInt();
                        graph.printPath(origin, destination);
                        distance = graph.floydMatrix[origin][destination];
                        if (distance == 999999) {
                            System.out.println("No existe ruta entre las ciudades");
                        }
                        else {
                            System.out.println("Distancia total: " + distance + " km");
                        }
                        System.out.println("-----------------------------------------------");
                        break;

                    case 2:
                        System.out.println("-----------------------------------------------");
                        String centralCity = graph.graphCenter();
                        System.out.println("La ciudad más céntrica es: " + centralCity);
                        System.out.println("-----------------------------------------------");
                        break;
                    
                    case 3:
                        System.out.println("-----------------------------------------------");
                        System.out.println("Qué desea modificar?");
                        System.out.println("1. Agregar ruta");
                        System.out.println("2. Eliminar ruta");
                        System.out.print("Seleccione una opción: ");
                        int modifyOption = scanner.nextInt();
                        switch (modifyOption) {
                            case 1:
                                System.out.println("-----------------------------------------------");
                                for (int i = 0; i < graph.vertexs.size(); i++) {
                                    String city = graph.printVertexs(i);
                                    System.out.println(i + ": " + city);
                                }
                                System.out.print("Ingrese el número de la ciudad de origen: ");
                                int v1 = scanner.nextInt();
                                System.out.print("Ingrese el número de la ciudad de destino: ");
                                int v2 = scanner.nextInt();
                                System.out.print("Ingrese la distancia: ");
                                distance = scanner.nextInt();
                                graph.addEdge(v1, v2, distance);
                                break;
                            case 2:
                                System.out.println("-----------------------------------------------");
                                for (int i = 0; i < graph.vertexs.size(); i++) {
                                    String city = graph.printVertexs(i);
                                    System.out.println(i + ": " + city);
                                }
                                System.out.print("Ingrese el número de la ciudad de origen: ");
                                int u = scanner.nextInt();
                                System.out.print("Ingrese el número de la ciudad de destino: ");
                                int w = scanner.nextInt();
                                graph.removeEdge(u, w);
                                break;
                        }
                        System.out.println("-----------------------------------------------");
                        break;
                    case 4:
                        System.out.println("-----------------------------------------------");
                        System.out.println("Matriz de ciudades:");
                        graph.printGraph();
                        System.out.println("-----------------------------------------------");
                        System.out.println("Matriz de distancias:");
                        graph.printFloyd(graph.floydMatrix);
                        System.out.println("-----------------------------------------------");
                        System.out.println("Todos los caminos más cortos:");
                        for (int i = 0; i < graph.vertexs.size(); i++) {
                            for (int j = 0; j < graph.vertexs.size(); j++) {
                                if (i != j) {
                                    System.out.print("Ruta más corta para ir desde " + graph.getVertexName(i) + " a " + graph.getVertexName(j) + ": ");
                                    graph.printPath(i, j);
                                    int dist = graph.floydMatrix[i][j];
                                    if (dist == 999999) {
                                        System.out.println("La ruta entre las ciudades es demasiado larga o no existe");
                                    }
                                    else {
                                        System.out.println("Distancia total: " + dist + " km");
                                    }
                                }
                            }
                        }
                        System.out.println("-----------------------------------------------");
                        break;

                    case 5:
                        System.out.println("-----------------------------------------------");
                        System.out.println("Saliendo del programa...");
                        scanner.close();
                        break;
                }
            } while (options < 1 || options > 5);
        } while (options != 5);
    }
}