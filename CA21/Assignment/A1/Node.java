class Node {
    String data;
    Node next;

    public Node() {
        next = null;
    }
    
    public Node(String data) {
        this.data = data;
        next = null;
    }

    public Node(String data, Node next) {
        this.data = data;
        this.next = next;
    }
}