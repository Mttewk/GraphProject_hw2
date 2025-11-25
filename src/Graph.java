import java.util.NoSuchElementException;

public class Graph<V> {
    private final MyArrayList<V> vertices = new MyArrayList<>();
    private final MyArrayList<MyArrayList<Edge<V>>> adj = new MyArrayList<>();
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(V v) {
        if (v == null) {
            throw new IllegalArgumentException("Вершина не может быть null");
        }
        if (contains(vertices, v)) {
            return;
        }
        vertices.add(v);
        adj.add(new MyArrayList<>());
    }

    public void addEdge(V from, V to, int weight) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Вершины не могут быть null");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Вес ребра не может быть отрицательным");
        }

        addVertex(from);
        addVertex(to);

        int i = indexOf(vertices, from);
        int j = indexOf(vertices, to);

        adj.get(i).add(new Edge<>(to, weight));
        if (!directed) {
            adj.get(j).add(new Edge<>(from, weight));
        }
    }

    public void addEdge(V from, V to) {
        addEdge(from, to, 1);
    }

    public void removeEdge(V from, V to) {
        if (from == null || to == null) return;

        int i = indexOf(vertices, from);
        int j = indexOf(vertices, to);
        if (i == -1 || j == -1) return; // нет вершины — ничего не делаем

        MyArrayList<Edge<V>> list = adj.get(i);
        for (int k = list.size() - 1; k >= 0; k--) {
            if (to.equals(list.get(k).to)) {
                list.removeAt(k);
            }
        }
        if (!directed) {
            list = adj.get(j);
            for (int k = list.size() - 1; k >= 0; k--) {
                if (from.equals(list.get(k).to)) {
                    list.removeAt(k);
                }
            }
        }
    }

    public void removeVertex(V v) {
        if (v == null) return;

        int index = indexOf(vertices, v);
        if (index == -1) return; // нет — ничего не делаем

        vertices.removeAt(index);
        adj.removeAt(index);

        // Удаляем все рёбра к этой вершине
        for (int i = 0; i < adj.size(); i++) {
            MyArrayList<Edge<V>> list = adj.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                if (v.equals(list.get(j).to)) {
                    list.removeAt(j);
                }
            }
        }
    }

    public MyArrayList<V> getAdjacent(V v) {
        if (v == null) {
            throw new IllegalArgumentException("Вершина не может быть null");
        }
        int index = indexOf(vertices, v);
        if (index == -1) {
            throw new NoSuchElementException("Вершина не существует: " + v);
        }

        MyArrayList<V> result = new MyArrayList<>();
        MyArrayList<Edge<V>> edges = adj.get(index);
        for (int i = 0; i < edges.size(); i++) {
            result.add(edges.get(i).to);
        }
        return result;
    }

    public void dfs(V start) {
        if (start == null) throw new IllegalArgumentException("Стартовая вершина null");
        if (indexOf(vertices, start) == -1) {
            throw new NoSuchElementException("Стартовая вершина не существует: " + start);
        }

        MyArrayList<V> visited = new MyArrayList<>();
        dfsHelper(start, visited);
        System.out.println();
    }

    private void dfsHelper(V v, MyArrayList<V> visited) {
        visited.add(v);
        System.out.print(v + " ");

        int i = indexOf(vertices, v);
        MyArrayList<Edge<V>> edges = adj.get(i);
        for (int j = 0; j < edges.size(); j++) {
            V to = edges.get(j).to;
            if (!contains(visited, to)) {
                dfsHelper(to, visited);
            }
        }
    }

    public void bfs(V start) {
        if (start == null) throw new IllegalArgumentException("Стартовая вершина null");
        if (indexOf(vertices, start) == -1) {
            throw new NoSuchElementException("Стартовая вершина не существует: " + start);
        }

        MyArrayList<V> visited = new MyArrayList<>();
        MyArrayList<V> queue = new MyArrayList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V v = queue.removeAt(0);
            System.out.print(v + " ");

            int i = indexOf(vertices, v);
            MyArrayList<Edge<V>> edges = adj.get(i);
            for (int j = 0; j < edges.size(); j++) {
                V to = edges.get(j).to;
                if (!contains(visited, to)) {
                    visited.add(to);
                    queue.add(to);
                }
            }
        }
        System.out.println();
    }

    public void print() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i) + " → " + adj.get(i));
        }
    }

    private int indexOf(MyArrayList<V> list, V v) {
        for (int i = 0; i < list.size(); i++) {
            if (v.equals(list.get(i))) return i;
        }
        return -1;
    }

    private boolean contains(MyArrayList<V> list, V v) {
        return indexOf(list, v) != -1;
    }
}