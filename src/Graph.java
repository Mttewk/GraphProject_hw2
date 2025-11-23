public class Graph {
    private final String[] vertices = new String[100];
    private final int[][] edges = new int[100][100];
    private int vertexCount = 0;

    public void addVertex(String v) {
        if (findVertex(v) == -1) {
            vertices[vertexCount++] = v;
        }
    }

    public void addEdge(String a, String b) {
        int i = findVertex(a);
        int j = findVertex(b);
        if (i == -1 || j == -1) return;
        edges[i][j] = 1;
        edges[j][i] = 1;
    }

    public void print() {
        for (int i = 0; i < vertexCount; i++) {
            System.out.print(vertices[i] + " -> ");
            for (int j = 0; j < vertexCount; j++) {
                if (edges[i][j] == 1) {
                    System.out.print(vertices[j] + " ");
                }
            }
            System.out.println();
        }
    }

    private int findVertex(String v) {
        for (int i = 0; i < vertexCount; i++) {
            if (vertices[i].equals(v)) return i;
        }
        return -1;
    }
}