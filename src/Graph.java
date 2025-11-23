import java.util.ArrayList;

public class Graph<V> {
    private final ArrayList<V> vertices = new ArrayList<>();
    private final ArrayList<ArrayList<V>> adj = new ArrayList<>();

    public void addVertex(V v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(V from, V to) {
        addVertex(from);
        addVertex(to);

        int i = vertices.indexOf(from);
        int j = vertices.indexOf(to);

        ArrayList<V>neighborFrom = adj.get(i);
        ArrayList<V>neighborTo = adj.get(j);

        if (!neighborFrom.contains(to)) {
            neighborFrom.add(to);
            neighborTo.add(from);
        }
    }

    public ArrayList<V> getAdjacent(V v) {
        int index = vertices.indexOf(v);
        if (index == -1) {
            return new ArrayList<>();
        }
        return new ArrayList<>(adj.get(index));
    }

    public void print() {
        for (int i =0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i) + " -> " + adj.get(i));
        }
    }
}