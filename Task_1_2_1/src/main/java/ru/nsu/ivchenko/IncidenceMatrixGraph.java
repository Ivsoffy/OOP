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
 * Представление графа в виде матрицы инцидентности.
 */
public class IncidenceMatrixGraph implements Graph {

    private int[][] incMtx; // -1 - from, 0, 1 - to
    private int vertexCount;
    private int edgeCount;

    /**
     * Конструктор без параметров.
     */
    public IncidenceMatrixGraph() {
        this.vertexCount = 0;
        this.edgeCount = 0;
        incMtx = new int[vertexCount + 1][edgeCount];
    }

    /**
     * Конструктор c количеством вершин и ребер.
     *
     * @param vertexCount - число вершин.
     * @param edgeCount   - число ребер.
     */
    public IncidenceMatrixGraph(int vertexCount, int edgeCount) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Некорректное количество вершин.");
        }
        this.vertexCount = vertexCount;
        this.edgeCount = edgeCount;
        incMtx = new int[vertexCount + 1][edgeCount];
    }

    /**
     * Добавляет вершину.
     *
     * @param vertex - номер вершины.
     */
    @Override
    public void addVertex(int vertex) {
        if (vertexCount < 0) {
            throw new IllegalArgumentException("Вершина должна быть положительным числом.");
        }
        if (vertex > vertexCount) {
            int[][] newMatrix = new int[vertex + 1][edgeCount];
            for (int i = 0; i <= vertexCount; i++) {
                System.arraycopy(incMtx[i], 0, newMatrix[i], 0, edgeCount);
            }
            incMtx = newMatrix;
            vertexCount = vertex;
        }
    }

    /**
     * Удаляет вершину.
     *
     * @param vertex - номер вершины.
     */
    @Override
    public void removeVertex(int vertex) {
        if (vertex > vertexCount) {
            throw new IllegalArgumentException("Нет такой вершины " + vertex);
        }
        // удаляем ребра которые исходят из этой вершины
        List<Integer> neighbours = getNeighbors(vertex);
        for (Integer neighbour : neighbours) {
            removeEdge(vertex, neighbour);
        }

        // удаляем остальные ребра
        Map<Integer, List<Integer>> edges = allEdges();
        for (Map.Entry<Integer, List<Integer>> entry : edges.entrySet()) {
            for (Integer neighbour : entry.getValue()) {
                if (neighbour == vertex) {
                    removeEdge(entry.getKey(), vertex);
                }
            }
        }
    }

    private int edgeInGraph(int from, int to) {
        for (int i = 0; i < edgeCount; i++) {
            if (incMtx[from][i] == -1 && incMtx[to][i] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Добавляет ребро.
     *
     * @param from - первая вершина
     * @param to-  вторая вершина
     */
    @Override
    public void addEdge(int from, int to) {
        if (from > vertexCount || to > vertexCount || from < 0 || to < 0) {
            throw new IllegalArgumentException("Неверно указаны вершины.");
        }
        if (edgeInGraph(from, to) == -1) {
            int[][] newMatrix = new int[vertexCount + 1][edgeCount + 1];
            for (int i = 0; i <= vertexCount; i++) {
                System.arraycopy(incMtx[i], 0, newMatrix[i], 0, edgeCount);
            }

            newMatrix[from][edgeCount] = -1;
            newMatrix[to][edgeCount] = 1;

            incMtx = newMatrix;
            edgeCount++;
        }
    }

    /**
     * Удаляет ребро.
     *
     * @param from - первая вершина
     * @param to   - вторая вершина
     */
    @Override
    public void removeEdge(int from, int to) {
        if (from > vertexCount || to > vertexCount || from < 0 || to < 0) {
            throw new IllegalArgumentException("Неверно указаны вершины.");
        }
        int edge = edgeInGraph(from, to);
        if (edge != -1) {
            int[][] newMatrix = new int[vertexCount + 1][edgeCount - 1];
            for (int j = 0; j <= vertexCount; j++) {
                System.arraycopy(incMtx[j], 0, newMatrix[j], 0, edge);
                if (edge < edgeCount - 1) {
                    System.arraycopy(incMtx[j], edge + 1, newMatrix[j], edge, edgeCount - edge - 1);
                }
            }
            incMtx = newMatrix;
            edgeCount--;
        }
//        } else {
//            throw new IllegalArgumentException("Нет такого ребра");
//        }
    }

    /**
     * Возвращает список соседей.
     *
     * @param vertex - номер вершины.
     * @return - соседи этой вершины.
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (vertex > vertexCount || vertex <= 0) {
            throw new IllegalArgumentException("Вершины нет в графе.");
        }
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < edgeCount; i++) {
            if (incMtx[vertex][i] == -1) {
                for (int to = 1; to <= vertexCount; to++) {
                    if (incMtx[to][i] == 1) {
                        neighbors.add(to);
                    }
                }
            }
        }
        return neighbors;
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
        AdjacencyMatrixGraph other = (AdjacencyMatrixGraph) obj;

        return this.allEdges().equals(((Graph) obj).allEdges());
    }

    /**
     * Возвращает все ребра.
     *
     * @return - все ребра в виде хэш мапа.
     */
    @Override
    public Map<Integer, List<Integer>> allEdges() {
        Map<Integer, List<Integer>> edges = new HashMap<>();
        for (int i = 1; i <= vertexCount; i++) {
            edges.put(i, getNeighbors(i));
        }
        return edges;
    }

    /**
     * Возвращает все вершины.
     *
     * @return - все вершины в виде списка.
     */
    @Override
    public List<Integer> allVertexes() {
        List<Integer> vertexes = new ArrayList<>();
        for (int i = 1; i <= vertexCount; i++) {
            vertexes.add(i);
        }
        return vertexes;
    }

    /**
     * Топологическая сортировка.
     *
     * @return список отсортированных вершин.
     */
    @Override
    public List<Integer> topologicalSort() {
        Map<Integer, Integer> status = new HashMap<>();
        List<Integer> topoList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean hasCycle = false;

        for (int i = 1; i <= vertexCount; i++) {
            status.put(i, 0);
        }

        for (int i = 1; i <= vertexCount; i++) {
            if (status.get(i) == 0) {
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

        for (int i = 0; i < edgeCount; i++) {
            if (incMtx[vertex][i] == 1) {
                for (int j = 1; j <= vertexCount; j++) {
                    if (incMtx[j][i] == -1) {
                        if (status.get(j) == 0) {
                            if (dfs(j, status, stack)) {
                                return true;
                            }
                        } else if (status.get(j) == 1) {
                            return true;
                        }
                    }
                }
            }
        }

        status.put(vertex, 2);
        stack.push(vertex);
        return false;
    }
}
