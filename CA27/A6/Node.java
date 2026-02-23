class Node {
    Point data;
    Node prev, next;

    public Node () {
        data = null;
        prev = this;
        next = this;
    }

    public Node(Point data) {
        this.data = data;
        prev = next = this;
    }

}