public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge(0,1,5);
        graph.addEdge(1,2,3);
        graph.addEdge(0,2,20);
        graph.printGraph();
        int[][] result = graph.floyd();
        graph.printFloyd(result);
    }
}