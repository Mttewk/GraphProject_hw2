public class Main {
    public static void main(String[] args) {
        Graph<Character> g = new Graph<>();

        g.addEdge('A', 'B', 4);
        g.addEdge('A', 'C', 2);
        g.addEdge('B', 'D', 5);
        g.addEdge('C', 'D', 1);
        g.addEdge('D', 'E');

        g.print();

        System.out.println("\nСоседи вершины A: " + g.getAdjacent('A'));
    }
}