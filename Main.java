public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.printGraph();
        graph.addEdge(0, 1, 5);
        graph.addEdge(1, 2, 10);
        graph.printGraph();
    }
}