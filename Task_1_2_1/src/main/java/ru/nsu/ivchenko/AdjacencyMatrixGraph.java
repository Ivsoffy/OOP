package ru.nsu.ivchenko;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Представление ориентированного графа в виде матрицы смежности.
 */
public class AdjacencyMatrixGraph implements Graph {

    private boolean[][] adjMtx;
    private int vertexCount;

    /**
     * Конструктор без параметров.
     */
    public AdjacencyMatrixGraph() {
        this.vertexCount = 0;
        adjMtx = new boolean[vertexCount + 1][vertexCount + 1];
    }

    /**
     * Конструтор для графа.
     *
     * @param size - число вершин.
     */
    public AdjacencyMatrixGraph(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Неправильное значение size");
        }
        vertexCount = size;
        adjMtx = new boolean[vertexCount + 1][vertexCount + 1];
    }

    /**
     * Добавляет новую вершину.
     *
     * @param vertex - новая вершина.
     */
    @Override
    public void addVertex(int vertex) {
        if (vertex > vertexCount) {
            boolean[][] newMatrix = new boolean[vertex + 1][vertex + 1];
            for (int i = 0; i <= vertexCount; i++) {
                System.arraycopy(adjMtx[i], 0, newMatrix[i], 0, vertexCount + 1);
            }
            adjMtx = newMatrix;
            vertexCount = vertex;
        }
    }


    /**
     * Удаляет вершину.
     *
     * @param vertex - новая вершина.
     */
    @Override
    public void removeVertex(int vertex) {
        if (vertex > vertexCount) {
            throw new IllegalArgumentException("Нет такой вершины " + vertex);
        }
        for (int i = 1; i <= vertexCount; i++) {
            adjMtx[vertex][i] = false;
            adjMtx[i][vertex] = false;
        }
    }

    /**
     * Добавляет ребро.
     *
     * @param from - откуда.
     * @param to   - куда.
     */
    @Override
    public void addEdge(int from, int to) {
        if (from > vertexCount || to > vertexCount) {
            throw new IllegalArgumentException("Нет одной из вершин");
        }
        adjMtx[from][to] = true;
    }

    /**
     * Удаляет ребро.
     *
     * @param from - откуда.
     * @param to   - куда.
     */
    @Override
    public void removeEdge(int from, int to) {
        if (from > vertexCount || to > vertexCount) {
            throw new IllegalArgumentException("Нет одной из вершин");
        }
        adjMtx[from][to] = false;
    }

    /**
     * Возвращает список соседей.
     *
     * @param vertex - номер вершины.
     * @return - соседи этой вершины.
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (vertex > vertexCount) {
            throw new IllegalArgumentException("Нет такой вершины " + vertex);
        }

        List<Integer> neighbors = new ArrayList<>();
        for (int i = 1; i <= vertexCount; i++) {
            if (adjMtx[vertex][i]) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }

    @Override
    public List<Integer> allVertexes() {
        List<Integer> vertexes = new ArrayList<>();
        for (int i = 1; i <= vertexCount; i++) {
            vertexes.add(i);
        }
        return vertexes;
    }

    /**
     * Читает граф из файла. ver1 ver2 - то это ребро. ver1 - это вершина.
     *
     * @param filename - имя файла.
     * @throws IOException - возвращает исключение.
     */
    @Override
    public void loadFromFile(String filename) throws IOException {
        BufferedReader buf = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = buf.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 1) {
                addVertex(Integer.parseInt(parts[0]));
            } else {
                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                addEdge(from, to);
            }
        }
    }

    /**
     * Превращает граф в строку.
     *
     * @return строку.
     */
    @Override
    public String toString() {
        return allEdges().toString();
    }

    /**
     * Возвращает все ребра.
     *
     * @return - все ребра в виде хэш мапа.
     */
    @Override
    public Map<Integer, List<Integer>> allEdges() {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 1; i <= vertexCount; i++) {
            List<Integer> to = new ArrayList<>();
            for (int j = 1; j <= vertexCount; j++) {
                if (adjMtx[i][j]) {
                    to.add(j);
                }
            }
            adjList.put(i, to);
        }
        return adjList;
    }

    /**
     * Сравнивает два графа по вершинам и ребрам.
     *
     * @param obj с чем сравниваем.
     * @return true/false.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Graph)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        return this.allVertexes().equals(((Graph) obj).allVertexes()) &&
            this.allEdges().equals(((Graph) obj).allEdges());
    }

    /**
     * Топологическая сортировка.
     *
     * @return список отсортированных вершин.
     */
    @Override
    public List<Integer> topologicalSort() {
        Map<Integer, Integer> status = new HashMap<>();
        boolean hasCycle = false;
        List<Integer> topoList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= vertexCount; i++) {
            if (status.getOrDefault(i, 0) == 0) {
                hasCycle = dfs(i, status, stack);
                if (hasCycle) {
                    return new ArrayList<>();
                }
            }
        }

        while (!stack.isEmpty()) {
            topoList.add(stack.pop());
        }

        return topoList;
    }

    /**
     * DFS.
     *
     * @param vertex - вершина.
     * @param status - статус.
     * @param stack  - стек.
     * @return - true если есть цикл, иначе false.
     */
    private boolean dfs(int vertex, Map<Integer, Integer> status, Stack<Integer> stack) {
        status.put(vertex, 1);

        for (int i = 1; i <= vertexCount; i++) {
            if (adjMtx[vertex][i]) {
                if (status.getOrDefault(i, 0) == 0) {
                    if (dfs(i, status, stack)) {
                        return true;
                    }
                } else if (status.get(i) == 1) {
                    return true;
                }
            }
        }

        status.put(vertex, 2);
        stack.push(vertex);
        return false;
    }
}
