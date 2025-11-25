public class Graph<V> {
    private final MyArrayList<V> vertices = new MyArrayList<>();
    private final MyArrayList<MyArrayList<Edge<V>>> adj = new MyArrayList<>();
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }

    public void addVertex(V v) {
        if (!contains(vertices, v)) {
            vertices.add(v);
            adj.add(new MyArrayList<>());
        }
    }

    public void addEdge(V from, V to, int weight) {
        addVertex(from);
        addVertex(to);

        int i = indexOf(vertices, from);
        adj.get(i).add(new Edge<>(to, weight));

        if (!directed) {
            int j = indexOf(vertices, to);
            adj.get(j).add(new Edge<>(from, weight));
        }
    }

    public void addEdge(V from, V to) {
        addEdge(from, to, 1);
    }

    public MyArrayList<V> getAdjacent(V v) {
        MyArrayList<V> result = new MyArrayList<>();
        int index = indexOf(vertices, v);
        if (index == -1) return result;

        MyArrayList<Edge<V>> edges = adj.get(index);
        for (int i = 0; i < edges.size(); i++) {
            result.add(edges.get(i).to);
        }
        return result;
    }

    public void removeEdge(V from, V to) {
        int i = indexOf(vertices, from);
        if (i != -1) {
            MyArrayList<Edge<V>> list = adj.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                if (to.equals(list.get(j).to)) {
                    list.removeAt(j);
                }
            }
        }
        if (!directed) {
            int j = indexOf(vertices, to);
            if (j != -1) {
                MyArrayList<Edge<V>> list = adj.get(j);
                for (int k = list.size() - 1; k >= 0; k--) {
                    if (from.equals(list.get(k).to)) {
                        list.removeAt(k);
                    }
                }
            }
        }
    }

    public void removeVertex(V v) {
        int index = indexOf(vertices, v);
        if (index == -1) return;

        vertices.removeAt(index);
        adj.removeAt(index);

        // Удаляем все входящие рёбра
        for (int i = 0; i < adj.size(); i++) {
            MyArrayList<Edge<V>> list = adj.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                if (v.equals(list.get(j).to)) {
                    list.removeAt(j);
                }
            }
        }
    }

    public void dfs(V start) {
        if (indexOf(vertices, start) == -1) {
            System.out.println("Вершина " + start + " не существует");
            return;

        }
        java.util.Set<V> visited = new java.util.HashSet<>();
        dfsHelper(start, visited);
        System.out.println(); // просто перевод строки в конце
    }

    private void dfsHelper(V v, java.util.Set<V> visited) {
        visited.add(v);
        System.out.print(v + " "); // только вершина и пробел

        int i = indexOf(vertices, v);
        MyArrayList<Edge<V>> edges = adj.get(i);
        for (int j = 0; j < edges.size(); j++) {
            V to = edges.get(j).to;
            if (!visited.contains(to)) {
                dfsHelper(to, visited);
            }
        }
    }

    public void bfs(V start) {
        if (indexOf(vertices, start) == -1) {
            System.out.println("Вершина " + start + " не существует");
            return;
        }
        java.util.Set<V> visited = new java.util.HashSet<>();
        java.util.Queue<V> queue = new java.util.LinkedList<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            V v = queue.poll();
            System.out.print(v + " ");

            int i = indexOf(vertices, v);
            MyArrayList<Edge<V>> edges = adj.get(i);
            for (int j = 0; j < edges.size(); j++) {
                V to = edges.get(j).to;
                if (!visited.contains(to)) {
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