public class Main {
    public static void main(String[] args) {
        Graph<Character> undirected = new Graph<>(false);

        System.out.println("Ненаправленный граф:");
        undirected.addEdge('A', 'B', 5);
        undirected.addEdge('A', 'C', 10);
        undirected.addEdge('B', 'D', 15);
        undirected.print();

        undirected.dfs('A');
        undirected.bfs('A');

        System.out.println("\nСоседи вершины A: " + undirected.getAdjacent('A'));

        System.out.println("\nПосле удаления ребра B -> D:");
        undirected.removeEdge('B', 'D');
        undirected.print();

        System.out.println();

        System.out.println("Направленный граф:");

        Graph<Character> directed = new Graph<>(true);
        directed.addEdge('A', 'B', 5);
        directed.addEdge('B', 'C', 10);
        directed.addEdge('C', 'A', 15);
        directed.addEdge('B', 'D', 20);
        directed.print();

        directed.dfs('A');
        directed.bfs('A');

        System.out.println("\nСоседи вершины A: " + directed.getAdjacent('A'));
    }
}