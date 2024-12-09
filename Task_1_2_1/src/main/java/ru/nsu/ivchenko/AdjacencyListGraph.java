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
 * Представление ориентированного графа в виде списка смежности.
 */
public class AdjacencyListGraph implements Graph {

    private Map<Integer, List<Integer>> adjList;

    /**
     * Конструктор без параметров.
     */
    public AdjacencyListGraph() {
        adjList = new HashMap<>();
    }

    /**
     * Конструктор. Создает список вершин от 1 до size.
     *
     * @param size - количество вершин.
     */
    public AdjacencyListGraph(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Неправильное значение size");
        }
        adjList = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    /**
     * Добавляет новую вершину.
     *
     * @param vertexIndex - новая вершина.
     */
    @Override
    public void addVertex(int vertexIndex) {
        adjList.putIfAbsent(vertexIndex, new ArrayList<>());
    }

    /**
     * Удаляет вершину.
     *
     * @param vertex - номер вершины.
     */
    @Override
    public void removeVertex(int vertex) {
        if (!adjList.containsKey(vertex) || adjList.get(vertex) == null) {
            throw new IllegalArgumentException("Такой вершины не существует");
        }

        adjList.remove(vertex);
        for (List<Integer> neighbors : adjList.values()) {
            neighbors.remove(Integer.valueOf(vertex));
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
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            throw new IllegalArgumentException("Одной из вершин не существует");
        }
        List<Integer> neighbors = adjList.get(from);
        if (!neighbors.contains(to)) {
            neighbors.add(to);
        }
    }

    /**
     * Удаляет ребро.
     *
     * @param from - откуда.
     * @param to   - куда.
     */
    @Override
    public void removeEdge(int from, int to) {
        if (!adjList.containsKey(from) || !adjList.containsKey(to)) {
            throw new IllegalArgumentException("Одной из вершин не существует");
        }

        List<Integer> neighbors = adjList.get(from);
        if (neighbors != null) {
            neighbors.remove(Integer.valueOf(to));
        }
    }

    /**
     * Возвращает список соседей.
     *
     * @param vertex - номер вершины.
     * @return - соседи этой вершины.
     */
    @Override
    public List<Integer> getNeighbors(int vertex) {
        if (!adjList.containsKey(vertex) || adjList.get(vertex) == null) {
            throw new IllegalArgumentException("Такой вершины не существует");
        }
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    /**
     * Возвращает все ребра.
     *
     * @return - все ребра в виде хэш мапа.
     */
    @Override
    public Map<Integer, List<Integer>> allEdges() {
        return adjList;
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
        return adjList.toString();
    }

    @Override
    public List<Integer> allVertexes() {
        return new ArrayList<>(adjList.keySet());
    }

    /**
     * Сравнивает два графа.
     *
     * @param obj с чем сравниваем.
     * @return true/false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Graph)) {
            return false;
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
        List<Integer> topoList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        boolean hasCycle = false;

        for (Integer vertex : adjList.keySet()) {
            status.put(vertex, 0);
        }

        for (Integer vertex : adjList.keySet()) {
            if (status.get(vertex) == 0) {
                hasCycle = dfs(vertex, status, stack);
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

        for (Integer neighbor : adjList.get(vertex)) {
            if (status.get(neighbor) == 0) {
                if (dfs(neighbor, status, stack)) {
                    return true;
                }
            } else if (status.get(neighbor) == 1) {
                return true;
            }
        }

        status.put(vertex, 2);
        stack.push(vertex);
        return false;
    }

}