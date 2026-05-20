import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GraphTest {
    Graph graph = new Graph();

    @Test
    public void testAddEdge() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        graph.addEdge(0, 1, 10);
        assertEquals(10, graph.matrix[0][1]);
    }

    @Test
    public void testAddVertex() {
        graph.addVertex("CityA");
        assertEquals(1, graph.vertexs.size());
    }

    @Test
    public void testGraphCenter() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        graph.addVertex("CityC");
        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 5);
        String center = graph.graphCenter();
        assertEquals("CityA", center);

    }

    @Test
    public void testPrintFloyd() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        graph.addEdge(0, 1, 10);
        int[][] expected = {{0, 10}, {999999, 0}};
        assertEquals(expected[0][0], graph.floydMatrix[0][0]);
        assertEquals(expected[0][1], graph.floydMatrix[0][1]);
        assertEquals(expected[1][0], graph.floydMatrix[1][0]);
        assertEquals(expected[1][1], graph.floydMatrix[1][1]);

    }

    @Test
    public void testPrintGraph() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        graph.addEdge(0, 1, 10);
        graph.printGraph();

    }

    @Test
    public void testPrintPath() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        graph.addEdge(0, 1, 10);
        graph.printPath(0, 1);
    }

    @Test
    public void testPrintVertexs() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        String vertexName = graph.printVertexs(0);
        assertEquals("CityA", vertexName);
    }

    @Test
    public void testRemoveEdge() {
        graph.addVertex("CityA");
        graph.addVertex("CityB");
        graph.addEdge(0, 1, 10);
        graph.removeEdge(0, 1);
        graph.printGraph();
        assertEquals(-1, graph.matrix[0][1]);
    }
}
