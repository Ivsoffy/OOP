package ru.nsu.ivchenko;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Интерфейс графа.
 */
public interface Graph {

    void addVertex(int vertex);

    void removeVertex(int vertex);

    void addEdge(int from, int to);

    void removeEdge(int from, int to);

    List<Integer> getNeighbors(int vertex);

    void loadFromFile(String filename) throws IOException;

    String toString();

    boolean equals(Object obj);

    public Map<Integer, List<Integer>> allEdges();

    public List<Integer> allVertexes();

    List<Integer> topologicalSort();
}