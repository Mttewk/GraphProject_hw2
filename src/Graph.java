import java.util.ArrayList;

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
}