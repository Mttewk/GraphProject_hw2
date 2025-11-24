public class Edge<V> {
    public final V to;
    public final int weight;

    public Edge(V to, int weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return to + " (" + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge<?> e)) return false;
        return weight == e.weight && to.equals(e.to);
    }

    @Override
    public int hashCode() {
        return 31 * to.hashCode() + weight;
    }
}