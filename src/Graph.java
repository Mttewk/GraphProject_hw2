import java.util.ArrayList;

public class Graph {
    private final ArrayList<String> vertices = new ArrayList<>();
    private final ArrayList<ArrayList<String>> adj = new ArrayList<>();

    public void addVertex(String v) {
        if (!vertices.contains(v)) {
            vertices.add(v);
            adj.add(new ArrayList<>());
        }
    }

    public void addEdge(String a, String b) {
        addVertex(a);
        addVertex(b);
        int i = vertices.indexOf(a);
        int j = vertices.indexOf(b);
        if (!adj.get(i).contains(b)) {
            adj.get(i).add(b);
            adj.get(j).add(a);
        }
    }

    public void print() {
        for (int i =0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i) + " -> " + adj.get(i));
        }
    }

    private int findVertex(String v) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(v)) return i;
        }
        return -1;
    }
}