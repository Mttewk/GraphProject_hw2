public class Main {
    public static void main(String[] args) {
        Graph<Character> g = new Graph<>();

        g.addEdge('A', 'B');
        g.addEdge('A', 'C');
        g.addEdge('B', 'D');
        g.addEdge('C', 'D');
        g.addEdge('D', 'E');

        g.print();

        System.out.println("\nСоседи вершины 'A': " + g.getAdjacent('A'));
    }
}