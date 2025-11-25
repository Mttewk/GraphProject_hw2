import java.util.*;

public class Graph<V> {
    private final ArrayList<V> vertices = new ArrayList<>();
    private final ArrayList<ArrayList<Edge<V>>> adj = new ArrayList<>();
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(V v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(V from, V to, int weight) {
        addVertex(from);
        addVertex(to);

        int i = vertices.indexOf(from);
        adj.get(i).add(new Edge<>(to, weight));

        if (!directed) {
            int j = vertices.indexOf(to);
            adj.get(j).add(new Edge<>(from, weight));
        }
    }

    public void addEdge(V from, V to) {
        addEdge(from, to, 1);
    }

    public ArrayList<V> getAdjacent(V v) {
        ArrayList<V> result = new ArrayList<>();
        int index = vertices.indexOf(v);
        if (index == -1) {
            return result;
        }
        for (Edge<V> edge : adj.get(index)) {
            result.add(edge.to);
        }
        return result;
    }

    public void print() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i) + " -> " + adj.get(i));
        }
    }

    public void dfs(V start) {
        if (!vertices.contains(start)) {
            System.out.println("Вершины " + start + " нет в графе");
            return;
        }

        Set<V> visited = new HashSet<>();
        System.out.print("DFS от " + start + ": ");
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(V v, Set<V> visited) {
        visited.add(v);
        System.out.print(v + " ");

        int index = vertices.indexOf(v);
        for (Edge<V> edge : adj.get(index)) {
            V neighbor = edge.to;
            if (!visited.contains(neighbor)) {
                dfsHelper(neighbor, visited);
            }
        }
    }


    public void bfs(V start) {
        if (!vertices.contains(start)) {
            System.out.println("Вершина " + start + " не существует!");
            return;
        }

        Set<V> visited = new HashSet<>();
        Queue<V> queue = new LinkedList<>();

        queue.add(start);
        visited.add(start);

        System.out.print("BFS от " + start + ": ");

        while (!queue.isEmpty()) {
            V current = queue.poll();
            System.out.print(current + " ");

            int index = vertices.indexOf(current);
            for (Edge<V> edge : adj.get(index)) {
                V neighbor = edge.to;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }
}