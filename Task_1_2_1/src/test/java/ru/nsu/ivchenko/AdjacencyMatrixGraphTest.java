package ru.nsu.ivchenko;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class AdjacencyMatrixGraphTest {

    AdjacencyMatrixGraph mtx = new AdjacencyMatrixGraph(3);

    @Test
    void testConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new AdjacencyMatrixGraph(0));
    }

    @Test
    void addVertex2() {
        mtx.addEdge(1, 2);
        mtx.addVertex(5);
        assertEquals(mtx.toString(), "{1=[2], 2=[], 3=[], 4=[], 5=[]}");

    }

    @Test
    void removeVertex() {
        mtx.addVertex(5);
        mtx.addEdge(1, 2);
        mtx.addEdge(1, 3);
        mtx.addEdge(5, 2);

        mtx.removeVertex(1);
        assertEquals(mtx.toString(), "{1=[], 2=[], 3=[], 4=[], 5=[2]}");
    }

    @Test
    void removeVertexException() {
        assertThrows(IllegalArgumentException.class, () -> mtx.removeVertex(5));
    }

    @Test
    void addEdge() {
        mtx.addEdge(1, 2);
        assertEquals(mtx.toString(), "{1=[2], 2=[], 3=[]}");
    }

    @Test
    void addEdgeException1() {
        assertThrows(IllegalArgumentException.class, () -> mtx.addEdge(5, 2));
    }

    @Test
    void addEdgeException2() {
        assertThrows(IllegalArgumentException.class, () -> mtx.addEdge(2, 5));
    }

    @Test
    void removeEdge() {
        mtx.addEdge(1, 2);
        mtx.removeEdge(1, 2);

        assertEquals(mtx.toString(), "{1=[], 2=[], 3=[]}");
    }

    @Test
    void removeEdgeException1() {
        assertThrows(IllegalArgumentException.class, () -> mtx.addEdge(5, 2));
    }

    @Test
    void removeEdgeException2() {
        assertThrows(IllegalArgumentException.class, () -> mtx.addEdge(2, 5));
    }

    @Test
    void getNeighbors() {
        List<Integer> compared = new ArrayList<>();
        compared.add(2);
        compared.add(3);

        mtx.addEdge(1, 2);
        mtx.addEdge(1, 3);

        List<Integer> with = mtx.getNeighbors(1);

        assertEquals(compared, with);

    }

    @Test
    void getNeighborsException() {
        assertThrows(IllegalArgumentException.class, () -> mtx.getNeighbors(5));
    }

    @Test
    void allEdges() {
        mtx.addVertex(4);
        mtx.addEdge(1, 2);
        mtx.addEdge(1, 3);
        mtx.addEdge(2, 3);

        Map<Integer, List<Integer>> allEdges = mtx.allEdges();

        assertEquals(allEdges.toString(), "{1=[2, 3], 2=[3], 3=[], 4=[]}");
    }

    @Test
    void testEqualsSame() {
        assertTrue(mtx.equals(mtx));
    }

    @Test
    void testEqualsWithAdjMtxNew() {
        AdjacencyMatrixGraph newMtx = new AdjacencyMatrixGraph(3);
        newMtx.addVertex(1);
        newMtx.addVertex(2);
        newMtx.addVertex(3);
        newMtx.addEdge(3, 1);

        mtx.addEdge(3, 1);

        assertEquals(mtx, newMtx);
    }

    @Test
    void testEqualsWithAnotherClass() {
        List<Integer> example = new ArrayList<>();

        assertNotEquals(mtx, example);
    }

    @Test
    void testEqualsWithAdjListTrue() {
        AdjacencyListGraph newMtx = new AdjacencyListGraph();
        newMtx.addVertex(1);
        newMtx.addVertex(2);
        newMtx.addVertex(3);
        newMtx.addEdge(3, 1);

        mtx.addEdge(3, 1);

        assertEquals(mtx, newMtx);
    }

    @Test
    void testEqualsWithAdjMtxFalse() {
        AdjacencyListGraph newMtx = new AdjacencyListGraph();
        newMtx.addVertex(1);
        newMtx.addVertex(2);
        newMtx.addVertex(3);
        newMtx.addEdge(3, 1);
        newMtx.addEdge(1, 2);

        mtx.addEdge(3, 1);

        assertNotEquals(mtx, newMtx);
    }

    @Test
    void testEqualsWithIncMtxTrue() {
        IncidenceMatrixGraph newMtx = new IncidenceMatrixGraph(3, 1);
        newMtx.addVertex(1);
        newMtx.addVertex(2);
        newMtx.addVertex(3);
        newMtx.addEdge(3, 1);

        mtx.addEdge(3, 1);

        assertEquals(mtx.equals(newMtx), true);
    }

    @Test
    void testEqualsWithIncMtxFalse() {
        IncidenceMatrixGraph newMtx = new IncidenceMatrixGraph(3, 2);
        newMtx.addVertex(1);
        newMtx.addVertex(2);
        newMtx.addVertex(3);
        newMtx.addEdge(3, 1);
        newMtx.addEdge(1, 2);

        mtx.addEdge(3, 1);

        assertNotEquals(mtx, newMtx);
    }

    @Test
    void topologicalSort() {
        mtx.addVertex(4);
        mtx.addVertex(5);
        mtx.addVertex(6);
        mtx.addVertex(7);

        mtx.addEdge(1, 7);
        mtx.addEdge(2, 4);
        mtx.addEdge(2, 3);
        mtx.addEdge(3, 4);
        mtx.addEdge(3, 5);
        mtx.addEdge(3, 7);
        mtx.addEdge(4, 5);
        assertEquals(mtx.topologicalSort().toString(), "[6, 2, 3, 4, 5, 1, 7]");
    }

    @Test
    void topologicalSortCycle() {
        mtx.addEdge(1, 2);
        mtx.addEdge(2, 3);
        mtx.addEdge(3, 1);

        assertEquals(mtx.topologicalSort().toString(), "[]");
    }

    @Test
    void readFromFileTest() throws IOException {
        mtx = new AdjacencyMatrixGraph(1);
        mtx.loadFromFile("src/test/resources/file.txt");

        assertEquals(mtx.toString(), "{1=[], 2=[3], 3=[], 4=[1]}");
    }
}
