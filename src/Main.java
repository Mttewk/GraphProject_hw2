public class Main {
    public static void main(String[] args) {
        Graph<Character> undirected = new Graph<>(false);

        undirected.addEdge('A', 'B', 5);
        undirected.addEdge('A', 'C', 10);
        undirected.addEdge('B', 'D', 15);
        undirected.print();

        undirected.dfs('A');

        System.out.println();

        Graph<Character> directed = new Graph<>(true);
        directed.addEdge('A', 'B', 5);
        directed.addEdge('B', 'C', 10);
        directed.addEdge('C', 'A', 15);
        directed.addEdge('B', 'D', 20);
        directed.print();

        directed.dfs('A');


        System.out.println("\nСоседи вершины A: " + directed.getAdjacent('A'));
        System.out.println("\nСоседи вершины A: " + undirected.getAdjacent('A'));
    }
}